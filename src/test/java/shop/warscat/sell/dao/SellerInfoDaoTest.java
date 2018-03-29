package shop.warscat.sell.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.warscat.sell.model.SellerInfo;
import shop.warscat.sell.utils.KeyUtils;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {

    @Autowired
    private SellerInfoDao dao;

    @Test
    public void save(){
        SellerInfo info = new SellerInfo();
        info.setId(KeyUtils.getUniquId());
        info.setUsername("admin");
        info.setPassword("passwd");
        info.setOpenid("token");
        Assert.assertNotNull(dao.save(info));
    }


    @Test
    public void findByOpenid() {
        SellerInfo token = dao.findByOpenid("token");
        Assert.assertNotNull(token);
    }
}