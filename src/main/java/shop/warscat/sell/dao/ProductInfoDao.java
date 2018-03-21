package shop.warscat.sell.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import shop.warscat.sell.model.ProductInfo;

import java.util.List;

public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);
}
