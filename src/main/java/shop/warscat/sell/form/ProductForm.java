package shop.warscat.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-28
 * Time: 08:50
 */
@Data
public class ProductForm {

    private String productId;

    /**名称*/
    @NotEmpty(message = "名称必填")
    private String productName;

    /**单价*/
    @NotEmpty(message = "单价必填")
    private BigDecimal productPrice;

    /**库存*/
    @NotEmpty(message = "库存必填")
    private Integer productStock;

    /**描述*/
    private String productDescription;

    /**小图*/
    private String productIcon;

    /**状态 0正常1下架*/
    private Integer productStatus;

    /**类目编号*/
    @NotEmpty(message = "类目编号必填")
    private Integer categoryType;
}
