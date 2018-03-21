package shop.warscat.sell.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description: 支付状态
 * User: wars
 * Date: 2018-03-21
 * Time: 04:46
 */
@Getter
public enum PayStatusEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功")
    ;

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
