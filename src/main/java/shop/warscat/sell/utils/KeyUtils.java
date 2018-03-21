package shop.warscat.sell.utils;

import java.util.Base64;
import java.util.Date;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Description: 订单主键生成工具类
 * User: wars
 * Date: 2018-03-21
 * Time: 16:41
 */

public class KeyUtils {


    /**
     * 生成唯一主键
     * 格式：时间+随机数(6位)
     */
    public static synchronized String getUniquId(){
        String result;
        Random random = new Random();
        Integer ran = random.nextInt(262144)+262144;
        return System.currentTimeMillis()+String.valueOf(ran);
    }
}
