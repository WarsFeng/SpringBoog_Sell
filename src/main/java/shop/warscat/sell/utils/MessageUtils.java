package shop.warscat.sell.utils;

/**
 * Created with IntelliJ IDEA.
 * Description: 新订单消息通知,有新订单并且已支付,则messageCode加1
 * User: wars
 * Date: 2018-03-30
 * Time: 18:18
 */

public class MessageUtils {

    private static Integer messageCode = 0;

    public static void addMessage() {
        messageCode++;
    }

    public static void lessMessage() {
        messageCode--;
    }

    public static Integer getMessageCode() {
        return messageCode;
    }

}
