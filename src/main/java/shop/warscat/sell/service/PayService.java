package shop.warscat.sell.service;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import shop.warscat.sell.dto.OrderDTO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-24
 * Time: 18:56
 */

public interface PayService {

    WxPayMpOrderResult create(OrderDTO dto);

    WxPayOrderNotifyResult notify(String notifyData);

    //退款
    WxPayRefundResult refund(OrderDTO dto);
}
