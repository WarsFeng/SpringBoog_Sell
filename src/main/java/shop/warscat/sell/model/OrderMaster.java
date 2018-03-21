package shop.warscat.sell.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import shop.warscat.sell.enums.OrderStatusEnum;
import shop.warscat.sell.enums.PayStatusEnum;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 订单
 * User: wars
 * Date: 2018-03-21
 * Time: 04:33
 */
@Data
@Entity
@DynamicUpdate
public class OrderMaster {

    @Id
    /**订单id*/
    private String orderId;
    /**买家名*/
    private String buyerName;
    /**买家手机号*/
    private String buyerPhone;
    /**买家地址*/
    private String buyerAddress;
    /**买家微信openId*/
    private String buyerOpenid;
    /**订单总金额*/
    private BigDecimal orderAmount;
    /**订单状态 默认为0新下单*/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /**支付状态 默认0未支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;
    private Date updateTime;
}
