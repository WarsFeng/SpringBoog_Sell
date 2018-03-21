package shop.warscat.sell.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.warscat.sell.model.OrderDetail;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 订单详情Dao
 * User: wars
 * Date: 2018-03-21
 * Time: 12:23
 */

public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findByOrderId(String orderId);
}
