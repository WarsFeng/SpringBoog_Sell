package shop.warscat.sell.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import shop.warscat.sell.model.OrderMaster;

/**
 * Created with IntelliJ IDEA.
 * Description: 订单Dao
 * User: wars
 * Date: 2018-03-21
 * Time: 12:18
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);
}
