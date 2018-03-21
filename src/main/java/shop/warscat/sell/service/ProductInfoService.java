package shop.warscat.sell.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.warscat.sell.model.ProductInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 商品信息
 * User: wars
 * Date: 2018-03-21
 * Time: 00:38
 */

public interface ProductInfoService {

    ProductInfo findOneById(String productId);

    /**
     * @return 查询所有上架商品
     */
    List<ProductInfo> findUpAll();

    /**
     * @param pageable 分页
     * @return 分页查询
     */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

}
