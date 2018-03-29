package shop.warscat.sell.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {

    @Autowired
    SellerServiceImpl sellerService;

    private final String openid="token";

    @Test
    public void findSellerInfoByOpenid() {
        Assert.assertEquals(openid,sellerService.findSellerInfoByOpenid(openid).getOpenid());
    }
}