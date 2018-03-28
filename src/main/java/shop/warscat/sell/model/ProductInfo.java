package shop.warscat.sell.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import shop.warscat.sell.enums.ProductStatusEnmu;
import shop.warscat.sell.utils.EnumUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * Description: 商品信息
 * User: wars
 * Date: 2018-03-21
 * Time: 00:19
 */
@Data
@Entity
public class ProductInfo {

    @Id
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

    private Date createTime;
    private Date updateTime;

    @JsonIgnore
    public String getProductStatusEnum(){
        return Objects.requireNonNull(EnumUtils.getByCode(productStatus, ProductStatusEnmu.class)).getMassage();
    }

}
