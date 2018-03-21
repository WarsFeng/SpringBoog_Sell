package shop.warscat.sell.exception;

import shop.warscat.sell.enums.ResultEnum;

/**
 * Created with IntelliJ IDEA.
 * Description: 异常
 * User: wars
 * Date: 2018-03-21
 * Time: 14:54
 */

public class SellException extends RuntimeException {

    private Integer code;


    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
