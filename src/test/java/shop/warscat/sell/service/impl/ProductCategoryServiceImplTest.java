package shop.warscat.sell.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.warscat.sell.model.ProductCategory;
import shop.warscat.sell.service.ProductCategoryService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl service;

    @Test
    public void findOneById() {
        ProductCategory oneById = service.findOneById(1);
        Assert.assertEquals(new Integer(1),oneById.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = service.findAll();
        Assert.assertNotEquals(0,all.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> byCategoryTypeIn = service.findByCategoryTypeIn(Arrays.asList(2, 3));
        Assert.assertNotEquals(0,byCategoryTypeIn.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("啦了");
        productCategory.setCategoryType(3);
        ProductCategory save = service.save(productCategory);
        Assert.assertNotNull(save);
    }
}