package shop.warscat.sell.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.warscat.sell.VO.CategoryVO;
import shop.warscat.sell.VO.ProductVO;
import shop.warscat.sell.VO.ResultVO;
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

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping("list")
    public ResultVO<List<CategoryVO>> List() {
        //获得所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //获得商品类目
        List<Integer> categoryTypeList = new ArrayList<Integer>();
        categoryTypeList = productInfoList.stream()
                .map(a -> a.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> categoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        //封装
        List<CategoryVO> categoryVOList = new ArrayList<>();
        //封装CatagoryVO
        for (int i = 0; i < categoryList.size(); i++) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCategoryName(categoryList.get(i).getCategoryName());
            categoryVO.setCategoryType(categoryList.get(i).getCategoryType());
            ArrayList<ProductVO> productVOList = new ArrayList<>();
            //封装ProductVO
            for (int i1 = 0; i1 < productInfoList.size(); i1++) {
                if (productInfoList.get(i1).getCategoryType().equals(categoryList.get(i).getCategoryType())) {
                    ProductVO productVO = new ProductVO();
                    BeanUtils.copyProperties(productInfoList.get(i1), productVO);
                    productVOList.add(productVO);
                }
            }
            categoryVO.setProductVOList(productVOList);
            categoryVOList.add(categoryVO);
        }
        return ResultVOUtils.success(categoryVOList);
    }

}
