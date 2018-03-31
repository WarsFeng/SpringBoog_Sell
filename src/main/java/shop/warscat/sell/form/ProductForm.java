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
    private String productName;

    /**单价*/
    private BigDecimal productPrice;

    /**库存*/
    private Integer productStock;

    /**描述*/
    private String productDescription;

    /**小图*/
    private String productIcon;

    /**状态 0正常1下架*/
    private Integer productStatus;

    /**类目编号*/
    private Integer categoryType;
}
