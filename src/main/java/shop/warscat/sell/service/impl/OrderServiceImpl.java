package shop.warscat.sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.warscat.sell.dao.OrderDetailDao;
import shop.warscat.sell.dao.OrderMasterDao;
import shop.warscat.sell.dao.ProductInfoDao;
import shop.warscat.sell.dto.OrderDTO;
import shop.warscat.sell.model.OrderDetail;
import shop.warscat.sell.model.OrderMaster;
import shop.warscat.sell.model.ProductInfo;
import shop.warscat.sell.service.OrderService;

import java.util.List;

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
    @Autowired
    public OrderServiceImpl(OrderMasterDao dao, OrderDetailDao detailDao, ProductInfoDao productInfoDao) {
        this.dao = dao;
        this.detailDao = detailDao;
        this.productInfoDao = productInfoDao;
    }

    @Override
    public OrderMaster create(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //前台数据
            //遍历订单商品处理
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            //每一条订单数据
            ProductInfo productInfo = productInfoDao.findById(orderDetail.getProductId()).orElse(new ProductInfo());
            //注入防护
            orderDetail.setProductPrice(productInfo.getProductPrice());
            orderDetail.setProductName(productInfo.getProductName());
            //TODO 如果商品没上架抛异常(待完善)
//            orderDetail.set(productInfo.getProductName());
//            orderDetail.setProductName(productInfo.getProductName());
            //数量
            Integer productNum = orderDetail.getProductQuantity();
        }
            //买家信息数据
        orderMaster.setBuyerName(orderDTO.getBuyerName());
        orderMaster.setBuyerAddress(orderDTO.getBuyerAddress());
        orderMaster.setBuyerOpenid(orderDTO.getBuyerOpenid());
        orderMaster.setBuyerPhone(orderDTO.getBuyerPhone());
//        orderMaster.setOrderId();

        return null;
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
