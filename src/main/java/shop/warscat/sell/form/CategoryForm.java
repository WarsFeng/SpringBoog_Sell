package shop.warscat.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-29
 * Time: 11:52
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /**商品分类名称*/
    @NotEmpty(message = "名称不能为空")
    private String categoryName;

    /**商品分类类型*/
    private Integer categoryType;

}
