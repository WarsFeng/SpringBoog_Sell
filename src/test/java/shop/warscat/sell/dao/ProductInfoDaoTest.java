package shop.warscat.sell.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.warscat.sell.model.ProductInfo;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    ProductInfoDao dao;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> byProductStatus = dao.findByProductStatus(0);
        Assert.assertNotEquals(0,byProductStatus.size());
    }
}