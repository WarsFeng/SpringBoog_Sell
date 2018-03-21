package shop.warscat.sell.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.warscat.sell.vo.CategoryVO;
import shop.warscat.sell.vo.ProductVO;
import shop.warscat.sell.vo.ResultVO;
import shop.warscat.sell.model.ProductCategory;
import shop.warscat.sell.model.ProductInfo;
import shop.warscat.sell.service.ProductCategoryService;
import shop.warscat.sell.service.ProductInfoService;
import shop.warscat.sell.utils.ResultVOUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description: 购买商品
 * User: wars
 * Date: 2018-03-21
 * Time: 01:18
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    private final ProductInfoService productInfoService;
    private final ProductCategoryService productCategoryService;

    @Autowired
    public BuyerProductController(ProductInfoService productInfoService, ProductCategoryService productCategoryService) {
        this.productInfoService = productInfoService;
        this.productCategoryService = productCategoryService;
    }

    @RequestMapping("list")
    public ResultVO List() {
        //获得所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //获得商品类目
        List<Integer> categoryTypeList;
        categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        List<ProductCategory> categoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        //封装
        List<CategoryVO> categoryVOList = new ArrayList<>();
        //封装CatagoryVO
        for (ProductCategory aCategoryList : categoryList) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCategoryName(aCategoryList.getCategoryName());
            categoryVO.setCategoryType(aCategoryList.getCategoryType());
            ArrayList<ProductVO> productVOList = new ArrayList<>();
            //封装ProductVO
            for (ProductInfo aProductInfoList : productInfoList) {
                if (aProductInfoList.getCategoryType().equals(aCategoryList.getCategoryType())) {
                    ProductVO productVO = new ProductVO();
                    BeanUtils.copyProperties(aProductInfoList, productVO);
                    productVOList.add(productVO);
                }
            }
            categoryVO.setProductVOList(productVOList);
            categoryVOList.add(categoryVO);
        }
        return ResultVOUtils.success(categoryVOList);
    }

}
