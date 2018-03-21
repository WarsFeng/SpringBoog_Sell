package shop.warscat.sell.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.warscat.sell.model.ProductInfo;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    ProductInfoServiceImpl service;

    @Test
    public void findOneById() {
        Assert.assertNotNull(service.findOneById("1"));
    }

    @Test
    public void findUpAll() {
        Assert.assertNotEquals(0,service.findUpAll());
    }

    @Test
    public void findAll() {
        Page<ProductInfo> page = service.findAll(new PageRequest(0, 1));
        System.out.println(page.getTotalElements());
        System.out.println(page.getContent().get(0).getProductName());
//        Assert.assertNotEquals(0, page);
    }

    @Test
    public void save() {
    }
}