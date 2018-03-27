package shop.warscat.sell.service;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
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
    /**分页查询单个用户的订单列表*/
    Page<OrderDTO> findListByOpenid(String buyerOpenId, Pageable pageable);

    /**
     * 分页查询全部订单列表
     * @param pageable 分页
     * @return 所有订单列表分页
     */
    Page<OrderMaster> findList(Pageable pageable);

    /**查询单个订单*/
    OrderDTO findOne(String openid,String orderId);

    /**
     * @param orderId 订单Id
     * @return 校验订单和订单详情是否存在并返回
     */
    OrderDTO findOneById(String orderId);
    /**取消订单*/
    Boolean cancel(String openid,String orderId);

    /**
     * 卖家取消订单
     * @param orderId 订单Id
     * @return 是否成功
     */
    Boolean adminCancel(String orderId);
    /**完结订单*/
    Boolean finish(String openid,String orderId);
    Boolean adminFinish(String orderId);
    /**支付订单*/
    Boolean paid(WxPayOrderNotifyResult result, String orderId);
}
