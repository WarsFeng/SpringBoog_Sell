package shop.warscat.sell.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description: 商品状态
 * User: wars
 * Date: 2018-03-21
 * Time: 00:50
 */
@Getter
public enum ProductStatusEnmu implements CodeEnum {

    UP(0,"上架"),
    DOWN(1,"下架"),
    ;

    private Integer code;

    private String massage;

    ProductStatusEnmu(Integer code, String massage) {
        this.code = code;
        this.massage = massage;
    }
}
