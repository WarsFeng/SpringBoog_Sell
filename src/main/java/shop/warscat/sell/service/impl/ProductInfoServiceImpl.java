package shop.warscat.sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.warscat.sell.dao.ProductInfoDao;
import shop.warscat.sell.enums.ProductStatusEnmu;
import shop.warscat.sell.model.ProductInfo;
import shop.warscat.sell.service.ProductInfoService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-21
 * Time: 00:45
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao dao;

    @Override
    public ProductInfo findOneById(String productId) {
        return dao.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return dao.findByProductStatus(ProductStatusEnmu.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return dao.save(productInfo);
    }
}
