package shop.warscat.sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.warscat.sell.dao.SellerInfoDao;
import shop.warscat.sell.model.SellerInfo;
import shop.warscat.sell.service.SellerService;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-29
 * Time: 16:11
 */
@Service
public class SellerServiceImpl implements SellerService {

    private final SellerInfoDao sellerInfoDao;

    @Autowired
    public SellerServiceImpl(SellerInfoDao sellerInfoDao) {
        this.sellerInfoDao = sellerInfoDao;
    }

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoDao.findByOpenid(openid);
    }

    @Override
    public boolean login(String username, String password) {
        SellerInfo byUsername = sellerInfoDao.findByUsername(username);
        if (byUsername!=null && byUsername.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
