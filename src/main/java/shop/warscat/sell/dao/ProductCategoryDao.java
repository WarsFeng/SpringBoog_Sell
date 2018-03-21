package shop.warscat.sell.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.warscat.sell.model.ProductCategory;

import java.util.List;

public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
