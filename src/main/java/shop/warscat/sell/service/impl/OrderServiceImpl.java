package shop.warscat.sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.warscat.sell.dao.OrderDetailDao;
import shop.warscat.sell.dao.OrderMasterDao;
import shop.warscat.sell.dao.ProductInfoDao;
import shop.warscat.sell.dto.CartDTO;
import shop.warscat.sell.dto.OrderDTO;
import shop.warscat.sell.enums.ResultEnum;
import shop.warscat.sell.exception.SellException;
import shop.warscat.sell.model.OrderDetail;
import shop.warscat.sell.model.OrderMaster;
import shop.warscat.sell.model.ProductInfo;
import shop.warscat.sell.service.OrderService;
import shop.warscat.sell.service.ProductInfoService;
import shop.warscat.sell.utils.KeyUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMasterDao dao;
    private final OrderDetailDao detailDao;
    private final ProductInfoDao productInfoDao;
    private final ProductInfoService productService;
    @Autowired
    public OrderServiceImpl(OrderMasterDao dao, OrderDetailDao detailDao, ProductInfoDao productInfoDao, ProductInfoService productService) {
        this.dao = dao;
        this.detailDao = detailDao;
        this.productInfoDao = productInfoDao;
        this.productService = productService;
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
            if (!byId.isPresent()||byId.get().getProductStatus()==1) {
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
        return orderMaster.getOrderId();
    }

    @Override
    public Page<OrderMaster> findList(String buyerOpenId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderMaster findOne(String orderId) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
