package shop.warscat.sell.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.warscat.sell.dto.CartDTO;
import shop.warscat.sell.dto.OrderDTO;
import shop.warscat.sell.model.OrderDetail;

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
    }

    @Test
    public void findOne() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}