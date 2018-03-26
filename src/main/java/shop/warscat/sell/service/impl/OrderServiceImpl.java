package shop.warscat.sell.service.impl;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayBaseRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import shop.warscat.sell.converter.OrderMaster2OrderDTOConverter;
import shop.warscat.sell.dao.OrderDetailDao;
import shop.warscat.sell.dao.OrderMasterDao;
import shop.warscat.sell.dao.ProductInfoDao;
import shop.warscat.sell.dto.CartDTO;
import shop.warscat.sell.dto.OrderDTO;
import shop.warscat.sell.enums.OrderStatusEnum;
import shop.warscat.sell.enums.PayStatusEnum;
import shop.warscat.sell.enums.ResultEnum;
import shop.warscat.sell.exception.SellException;
import shop.warscat.sell.model.OrderDetail;
import shop.warscat.sell.model.OrderMaster;
import shop.warscat.sell.model.ProductInfo;
import shop.warscat.sell.service.OrderService;
import shop.warscat.sell.service.PayService;
import shop.warscat.sell.service.ProductInfoService;
import shop.warscat.sell.utils.KeyUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-21
 * Time: 14:15
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMasterDao dao;
    private final OrderDetailDao detailDao;
    private final ProductInfoDao productInfoDao;
    private final ProductInfoService productService;
    private final PayService payService;

    @Autowired
    public OrderServiceImpl(OrderMasterDao dao, OrderDetailDao detailDao, ProductInfoDao productInfoDao
            , ProductInfoService productService, @Lazy PayService payService) {
        this.dao = dao;
        this.detailDao = detailDao;
        this.productInfoDao = productInfoDao;
        this.productService = productService;
        this.payService = payService;
    }

    @Override
    @Transactional
    public String create(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(KeyUtils.getUniquId());
        //遍历订单商品处理
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        //BigInteger.ZERO减少新的实例创建
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDetailList) {
            //每一条订单数据
            Optional<ProductInfo> byId = productInfoDao.findById(orderDetail.getProductId());
            //注入防护
            if (!byId.isPresent() || byId.get().getProductStatus() == 1) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = byId.get();
            orderDetail.setProductName(productInfo.getProductName());
            orderDetail.setProductPrice(productInfo.getProductPrice());
            orderDetail.setProductIcon(productInfo.getProductIcon());
            //加入到订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //Id
            orderDetail.setOrderId(orderMaster.getOrderId());
            orderDetail.setDetailId(KeyUtils.getUniquId());
            detailDao.save(orderDetail);
        }

        //库存
        List<CartDTO> dtoList = orderDetailList.stream()
                .map(a -> new CartDTO(a.getProductId(), a.getProductQuantity()))
                .collect(Collectors.toList());
        productService.descreaseStock(dtoList);

        //订单数据
        orderMaster.setBuyerName(orderDTO.getBuyerName());
        orderMaster.setBuyerPhone(orderDTO.getBuyerPhone());
        orderMaster.setBuyerAddress(orderDTO.getBuyerAddress());
        orderMaster.setBuyerOpenid(orderDTO.getBuyerOpenid());
        orderMaster.setOrderAmount(orderAmount);

        OrderMaster save = dao.save(orderMaster);
        return save.getOrderId();
    }

    @Override
    public Page<OrderDTO> findListByOpenid(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = dao.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderMaster> orderList = orderMasterPage.getContent();
        //转换
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.converter(orderList);
        return new PageImpl<>(orderDTOList);
    }

    @Override
    public Page<OrderMaster> findList(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public OrderDTO findOne(String openid,String orderId) {
        OrderDTO dto = new OrderDTO();
        Optional<OrderMaster> byId = dao.findById(orderId);
        if (!byId.isPresent()) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if (!byId.get().getBuyerOpenid().equals(openid)) {
            throw new SellException(ResultEnum.RARAM_ERROR);
        }
        BeanUtils.copyProperties(byId.get(), dto);


        List<OrderDetail> detailList = detailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(detailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        dto.setOrderDetailList(detailList);
        return dto;
    }

    @Override
    public OrderDTO findOneById(String orderId) {
        Optional<OrderMaster> byId = dao.findById(orderId);
        OrderDTO dto = new OrderDTO();
        if (!byId.isPresent()) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        BeanUtils.copyProperties(byId.get(), dto);
        List<OrderDetail> detailList = detailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(detailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        dto.setOrderDetailList(detailList);
        return dto;
    }

    //前台传入参数为openid和订单id
    @Override
    @Transactional
    public Boolean cancel(String openid,String orderId) {
        Optional<OrderMaster> byId = dao.findById(orderId);
        if (!byId.isPresent()) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //订单id的买家id和传入的一样并且订单状态为新下单
        OrderMaster findOrder = byId.get();
        if (!(findOrder.getBuyerOpenid().equals(openid) && findOrder.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))) {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        findOrder.setOrderStatus(OrderStatusEnum.CANCEL.getCode());

        //返回库存
        List<OrderDetail> orderDetailList = detailDao.findByOrderId(orderId);
        //为空则抛异常
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDER_DATAIL_EMTRY);
        }
        List<CartDTO> proList = new ArrayList<>();
        for (OrderDetail detail : orderDetailList) {
            CartDTO cartDTO = new CartDTO(detail.getProductId(), detail.getProductQuantity());
            proList.add(cartDTO);
        }
        productService.increaseStock(proList);

        //退款功能
        if (findOrder.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            OrderDTO dto = new OrderDTO();
            dto.setOrderId(orderId);
            WxPayRefundResult refund = payService.refund(dto);
            log.info("[微信][取消订单退款]Response:{}",refund);
        }

        dao.save(findOrder);
        return true;
    }

    //前台传入参数为openid和订单id
    @Override
    @Transactional
    public Boolean finish(String openid,String orderId) {
        Optional<OrderMaster> byId = dao.findById(orderId);
        if (!byId.isPresent()) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //订单id的买家id和传入的一样并且支付状态为新下单
        OrderMaster findOrder = byId.get();
        if (!(findOrder.getBuyerOpenid().equals(openid))) {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        if (findOrder.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            throw new SellException(ResultEnum.ORDER_NOT_PAID);
        }
        findOrder.setOrderStatus(OrderStatusEnum.FINITHED.getCode());
        dao.save(findOrder);
        return true;
    }

    //前台传入参数为openid和订单id
    //支付订单
    @Override
    @Transactional
    public Boolean paid(WxPayOrderNotifyResult result, String orderId) {
        Optional<OrderMaster> byId = dao.findById(orderId);
        if (!byId.isPresent()) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        OrderMaster findOrder = byId.get();
        //订单状态必须为未付款状态和新创建状态
        if (findOrder.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            throw new SellException(ResultEnum.ORDER_YES_PAID);
        }
        //校验微信返回报文内金额是否正确
        Integer resultTotalFee = result.getTotalFee();
        Integer orderTotalFee = WxPayBaseRequest.yuanToFee(findOrder.getOrderAmount().toString());
        if (!resultTotalFee.equals(orderTotalFee)) {
            log.error("[微信][支付]订单Id{}入款金额{},实际金额{}",orderId,resultTotalFee,orderTotalFee);
            throw new SellException(ResultEnum.WX_INPUT_FEE_ERROR);
        }
        //修改
        //!支付订单不能修改订单状态 只能修改订单支付状态
        // 订单状态是商家修改的!
//        findOrder.setOrderStatus(OrderStatusEnum.FINITHED.getCode());
        findOrder.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        dao.save(findOrder);
        return true;
    }
}
