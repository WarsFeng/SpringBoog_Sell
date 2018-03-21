package shop.warscat.sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.warscat.sell.dao.ProductCategoryDao;
import shop.warscat.sell.model.ProductCategory;
import shop.warscat.sell.service.ProductCategoryService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:商品类型
 * User: wars
 * Date: 2018-03-20
 * Time: 23:54
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao dao;

    @Override
    public ProductCategory findOneById(Integer categoryId) {
        return dao.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return dao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return dao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return dao.save(productCategory);
    }
}
