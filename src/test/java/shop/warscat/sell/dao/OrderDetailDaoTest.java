package shop.warscat.sell.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.warscat.sell.model.OrderDetail;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    OrderDetailDao dao;

    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("asdasd");
        orderDetail.setOrderId("12354121");
        orderDetail.setProductId("1");
        orderDetail.setProductName("这是一个商品名");
        orderDetail.setProductPrice(new BigDecimal(123));
        orderDetail.setProductQuantity(12);
        Assert.assertNotNull(dao.save(orderDetail));

    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> byOrderId = dao.findByOrderId("12354121");
        Assert.assertNotEquals(0,byOrderId.size());
    }
}