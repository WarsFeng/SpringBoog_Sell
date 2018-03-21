package shop.warscat.sell.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description: 订单详情
 * User: wars
 * Date: 2018-03-21
 * Time: 04:50
 */
@Data
@Entity
public class OrderDetail {
    @Id
    private String detailId;
    /**订单Id*/
    private String orderId;
    /**商品id*/
    private String productId;
    /**商品名称*/
    private String productName;
    /**商品单价*/
    private BigDecimal productPrice;
    /**商品数量*/
    private Integer productQuantity;
    /**商品小图*/
    private String productIcon;
}
