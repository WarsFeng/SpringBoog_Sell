package shop.warscat.sell.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.warscat.sell.model.SellerInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-29
 * Time: 15:59
 */

public interface SellerInfoDao extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openid);

    SellerInfo findByUsername(String username);

}
