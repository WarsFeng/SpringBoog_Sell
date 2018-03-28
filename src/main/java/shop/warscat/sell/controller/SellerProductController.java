package shop.warscat.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.warscat.sell.enums.ResultEnum;
import shop.warscat.sell.exception.SellException;
import shop.warscat.sell.model.ProductCategory;
import shop.warscat.sell.model.ProductInfo;
import shop.warscat.sell.service.ProductCategoryService;
import shop.warscat.sell.service.ProductInfoService;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 卖家商品
 * User: wars
 * Date: 2018-03-27
 * Time: 17:55
 */
@Slf4j
@RestController
@RequestMapping("/seller/product")
public class SellerProductController {

    private final ProductInfoService productService;

    private final ProductCategoryService productCategoryService;

    @Autowired
    public SellerProductController(ProductCategoryService productCategoryService, ProductInfoService productService) {
        this.productCategoryService = productCategoryService;
        this.productService = productService;
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "5") Integer size, Map<String, Object> map) {
        Page<ProductInfo> productPage = productService.findAll(PageRequest.of(page - 1, size, Sort.Direction.DESC, "createTime"));
        map.put("productList", productPage);
        map.put("totalPage", productPage.getTotalPages());
        map.put("curentPage", page);
        map.put("pageSize", size);
        return new ModelAndView("product/list", map);
    }

    @GetMapping("/upProduct")
    public ModelAndView upProduct(@RequestParam("productId") String productId, Map<String, Object> map) {
        String returnUrl = "/sell/seller/product/list";
        try {
            productService.upProduct(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", returnUrl);
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.UP_SUCCES.getMessage());
        map.put("url", returnUrl);
        return new ModelAndView("common/succes", map);
    }

    @GetMapping("/downProduct")
    public ModelAndView downProduct(@RequestParam("productId") String productId, Map<String, Object> map) {
        String returnUrl = "/sell/seller/product/list";
        try {
            productService.downProduct(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", returnUrl);
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.DOWN_SUCCES.getMessage());
        map.put("url", returnUrl);
        return new ModelAndView("common/succes", map);
    }

    //商品新增和修改页面
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId, Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo product;
            try {
                product = productService.findOneById(productId);
            } catch (Exception e) {
                log.error("[商品][展示]id:{},msg:{}", productId, ResultEnum.PRODUCT_NOT_EXIST.getMessage());
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            map.put("product", product);
        }
        List<ProductCategory> categoryList = productCategoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("product/index",map);
    }


}
