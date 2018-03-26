package shop.warscat.sell.enums;

import lombok.Data;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description: 订单状态
 * User: wars
 * Date: 2018-03-21
 * Time: 04:39
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {
    NEW(0, "新订单"),
    FINITHED(1, "完结"),
    CANCEL(2, "已取消");

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
