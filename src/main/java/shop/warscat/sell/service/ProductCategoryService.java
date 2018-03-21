package shop.warscat.sell.service;

import shop.warscat.sell.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory findOneById(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
