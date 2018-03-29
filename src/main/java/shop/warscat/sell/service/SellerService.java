package shop.warscat.sell.service;

import shop.warscat.sell.model.SellerInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-29
 * Time: 16:10
 */

public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);

    boolean login(String username,String password);
}
