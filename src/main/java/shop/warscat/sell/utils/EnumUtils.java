package shop.warscat.sell.utils;

import shop.warscat.sell.enums.CodeEnum;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-26
 * Time: 13:53
 */

public class EnumUtils {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> tClass) {
        for (T each : tClass.getEnumConstants()) {
            if (each.getCode().equals(code)) {
                return each;
            }
        }
        return null;
    }
}
