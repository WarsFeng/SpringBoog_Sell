package shop.warscat.sell.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-21
 * Time: 15:38
 */
@Getter
public enum ResultEnum {

    RARAM_ERROR(1,"参数不正确"),
    JSON_CONVERTER_ERROR(2,"Json转换错误"),
    ORDER_CREATE_ERROR(3,"订单创建失败"),
    OPENID_EMTRY(4,"OpenId为空"),
    ORDER_PAY_ERROR(5,"订单支付失败"),
    PAY_AJAX_ERROR(6,"微信支付异步通知错误"),
    WX_INPUT_FEE_ERROR(7,"微信支付入账金额不正确"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态错误"),
    ORDER_DATAIL_EMTRY(15,"订单详情为空"),
    ORDER_NOT_PAID(16,"订单未支付"),
    ORDER_YES_PAID(17,"订单支付状态不正确"),
    ORDER_SAVE_ERROR(18,"订单保存错误"),
    WX_WEB_ERROR(19,"微信网页授权错误")
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
