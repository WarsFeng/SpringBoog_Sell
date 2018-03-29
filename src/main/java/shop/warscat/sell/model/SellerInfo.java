package shop.warscat.sell.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * Description: 卖家账户信息
 * User: wars
 * Date: 2018-03-29
 * Time: 15:55
 */

@Data
@Entity
public class SellerInfo {

    @Id
    private String id;

    private String username;

    private String password;

    private String openid;

}
