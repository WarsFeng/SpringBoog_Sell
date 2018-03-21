package shop.warscat.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import shop.warscat.sell.model.ProductInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 商品分类
 * User: wars
 * Date: 2018-03-21
 * Time: 01:41
 */
@Data
public class CategoryVO {

    /**分类名称*/
    @JsonProperty("name")
    private String categoryName;

    /**分类类型编号*/
    @JsonProperty("type")
    private Integer categoryType;

    /**商品集合*/
    @JsonProperty("foods")
    private List<ProductVO> productVOList;

}
