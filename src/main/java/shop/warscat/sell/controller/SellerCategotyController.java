package shop.warscat.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.warscat.sell.enums.ResultEnum;
import shop.warscat.sell.exception.SellException;
import shop.warscat.sell.form.CategoryForm;
import shop.warscat.sell.model.ProductCategory;
import shop.warscat.sell.service.ProductCategoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 卖家类目管理
 * User: wars
 * Date: 2018-03-28
 * Time: 19:25
 */
@Slf4j
@RestController
@RequestMapping("/seller/category")
public class SellerCategotyController {

    private final ProductCategoryService categoryService;

    @Autowired
    public SellerCategotyController(ProductCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = categoryService.findAll();
        if (CollectionUtils.isEmpty(categoryList)) {
            log.error("[分类][查询],分类不存在");
            map.put("msg", ResultEnum.CATEGORY_NOT_EXIST.getMessage());
            map.put("url", "/sell/seller/category/list");
            return new ModelAndView("common/error", map);
        }
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false)
                                      Integer categoryId, Map<String, Object> map) {
        if (StringUtils.isEmpty(categoryId)) {
            return new ModelAndView("category/index", map);
        }
        ProductCategory category;
        try {
            category = categoryService.findOneById(categoryId);
        } catch (SellException e) {
            log.error("[分类][查询]Id:{}msg;{}", categoryId, ResultEnum.RARAM_ERROR.getMessage());
            map.put("msg", ResultEnum.RARAM_ERROR.getMessage());
            map.put("url", "/sell/seller/category/list");
            return new ModelAndView("common/error", map);
        }
        map.put("category", category);
        return new ModelAndView("category/index", map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm, BindingResult bindingResult, Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        ProductCategory category = new ProductCategory();
        if (categoryForm.getCategoryId() != null) {
            category = categoryService.findOneById(categoryForm.getCategoryId());
        }
        //id主键自增不需要
        BeanUtils.copyProperties(categoryForm, category);
        try {
            categoryService.save(category);
        } catch (Exception e) {
            log.error("[分类][保存]失败,msg:{}", e.getMessage());
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.CATEGORY_ADD_SUCCES.getMessage());
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/succes", map);
    }
}
