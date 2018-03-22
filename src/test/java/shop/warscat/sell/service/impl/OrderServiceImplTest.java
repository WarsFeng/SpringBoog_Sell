package shop.warscat.sell.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.warscat.sell.dto.CartDTO;
import shop.warscat.sell.dto.OrderDTO;
import shop.warscat.sell.model.OrderDetail;
import shop.warscat.sell.model.OrderMaster;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    OrderServiceImpl service;

    @Test
    public void create() {
        OrderDTO dto = new OrderDTO();
        dto.setBuyerName("Wars");
        dto.setBuyerAddress("北京四合院");
        dto.setBuyerPhone("15555555555");
        dto.setBuyerOpenid("OPENIDIDIDIDID");

        List<OrderDetail> list = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("2");
        orderDetail.setProductQuantity(2);
        list.add(orderDetail);

        dto.setOrderDetailList(list);
        System.out.println(service.create(dto));
    }

    @Test
    public void findList() {
        Page<OrderDTO> orderDTOPage = service.findList("OPENIDIDIDIDID", new PageRequest(0, 5));
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void findOne() {
        OrderDTO one = service.findOne("OPENIDIDIDIDID","1521627668954352246");
        Assert.assertNotEquals(0,one.getOrderDetailList().size());
    }

    @Test
    public void cancel() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerOpenid("OPENIDIDIDIDID");
        orderMaster.setOrderId("1521627668954352246");
        Assert.assertNotEquals(false,service.cancel(orderMaster));
    }

    @Test
    public void finish() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerOpenid("OPENIDIDIDIDID");
        orderMaster.setOrderId("1521627668954352246");
        Assert.assertNotEquals(false,service.finish(orderMaster));
    }

    @Test
    public void paid() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerOpenid("OPENIDIDIDIDID");
        orderMaster.setOrderId("1521627668954352246");
        Assert.assertNotEquals(false,service.paid(orderMaster));
    }
}