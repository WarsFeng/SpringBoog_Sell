package shop.warscat.sell.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import shop.warscat.sell.enums.OrderStatusEnum;
import shop.warscat.sell.enums.PayStatusEnum;
import shop.warscat.sell.model.OrderDetail;
import shop.warscat.sell.utils.seriallazer.Date2LongSeriallazer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:订单包含订单详情列表
 * User: wars
 * Date: 2018-03-21
 * Time: 14:04
 */
@Data
public class OrderDTO {

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

    @JsonSerialize(using = Date2LongSeriallazer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSeriallazer.class)
    private Date updateTime;


    private List<OrderDetail> orderDetailList;
}
