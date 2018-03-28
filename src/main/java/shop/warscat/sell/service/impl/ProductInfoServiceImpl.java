package shop.warscat.sell.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.warscat.sell.dao.ProductInfoDao;
import shop.warscat.sell.dto.CartDTO;
import shop.warscat.sell.enums.ProductStatusEnmu;
import shop.warscat.sell.enums.ResultEnum;
import shop.warscat.sell.exception.SellException;
import shop.warscat.sell.model.ProductInfo;
import shop.warscat.sell.service.ProductInfoService;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-21
 * Time: 00:45
 */
@Slf4j
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    private final ProductInfoDao dao;

    @Autowired
    public ProductInfoServiceImpl(ProductInfoDao dao) {
        this.dao = dao;
    }

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


    //加减库存
    @Override
    public void increaseStock(List<CartDTO> proList) {
        for (CartDTO dto : proList) {
            Optional<ProductInfo> byId = dao.findById(dto.getProductId());
            ProductInfo productInfo = byId.get();
            productInfo.setProductStock(productInfo.getProductStock() + dto.getProductQuantiry());
            dao.save(productInfo);
        }
    }

    @Override
    public void descreaseStock(List<CartDTO> proList) {
        for (CartDTO cartDTO : proList) {
            ProductInfo productInfo = dao.findById(cartDTO.getProductId()).get();
            int newProductStock = productInfo.getProductStock() - cartDTO.getProductQuantiry();
            if (newProductStock < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(newProductStock);
            dao.save(productInfo);
        }
    }

    //上架商品
    @Override
    public void upProduct(String productId){
        Optional<ProductInfo> byId = dao.findById(productId);
        if (!byId.isPresent()) {
            log.error("[商品][查询]商品不存在id:{}",productId);
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        ProductInfo findProduct = byId.get();
        findProduct.setProductStatus(ProductStatusEnmu.UP.getCode());
        dao.save(findProduct);
    }

    @Override
    public void downProduct(String productId){
        Optional<ProductInfo> byId = dao.findById(productId);
        if (!byId.isPresent()) {
            log.error("[商品][查询]商品不存在id:{}",productId);
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        ProductInfo findProduct = byId.get();
        findProduct.setProductStatus(ProductStatusEnmu.DOWN.getCode());
        dao.save(findProduct);
    }
}
