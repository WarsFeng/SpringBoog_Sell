package shop.warscat.sell.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.warscat.sell.model.OrderMaster;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    OrderMasterDao dao;

    private final String OPEN_ID = "Wars";

    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerName("螺蛳粉");
        orderMaster.setOrderId("12354121");
        orderMaster.setBuyerAddress("海淀区啦啦啦了");
        orderMaster.setBuyerPhone("110");
        orderMaster.setBuyerOpenid("Wars");
        orderMaster.setOrderAmount(new BigDecimal(251));
        Assert.assertNotNull(dao.save(orderMaster));
    }

    @Test
    public void findByBuyerOpenid() {
        Page<OrderMaster> byBuyerOpenid = dao.findByBuyerOpenid(OPEN_ID, PageRequest.of(0, 2));
        Assert.assertNotEquals(0,byBuyerOpenid.getTotalElements());
    }

}