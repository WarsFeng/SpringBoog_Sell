package shop.warscat.sell.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.warscat.sell.dto.OrderDTO;
import shop.warscat.sell.model.OrderMaster;

/**
 * Created with IntelliJ IDEA.
 * Description:订单
 * User: wars
 * Date: 2018-03-21
 * Time: 13:58
 */

public interface OrderService {

    /**创建订单
     * @return orderId
     * */
    String create(OrderDTO orderDTO);
    /**查询订单列表*/
    Page<OrderDTO> findList(String buyerOpenId, Pageable pageable);
    /**查询单个订单*/
    OrderDTO findOne(String orderId);
    /**取消订单*/
    boolean cancel(OrderMaster order);
    /**完结订单*/
    boolean finish(OrderMaster orderDTO);
    /**支付订单*/
    OrderDTO paid(OrderDTO orderDTO);
}
