package shop.warscat.sell.dao;

import org.assertj.core.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.warscat.sell.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void findOneTest(){
        Optional<ProductCategory> productCategory = productCategoryDao.findById(1);
        System.out.println(productCategory.get());
    }

    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("Wars");
        productCategory.setCategoryType(2);
        System.out.println(productCategory);
        productCategoryDao.save(productCategory);
    }

    @Test
    public void updateTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(6);
        productCategory.setCategoryName("Wars");

//        productCategory.setUpdateTime(new Date());
        ProductCategory save = productCategoryDao.save(productCategory);
        System.out.println(save);
        Assert.assertNotNull(save);

    }

    @Test
    public void findByCategoryTypeIn(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(2);
        List<ProductCategory> byCategoryTypeIn = productCategoryDao.findByCategoryTypeIn(list);
        for (int i = 0; i < byCategoryTypeIn.size(); i++) {
            System.out.println(byCategoryTypeIn.get(i));
        }
    }


}