package shop.warscat.sell.service.impl;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayBaseRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.warscat.sell.dto.OrderDTO;
import shop.warscat.sell.enums.ResultEnum;
import shop.warscat.sell.exception.SellException;
import shop.warscat.sell.service.OrderService;
import shop.warscat.sell.service.PayService;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-24
 * Time: 19:00
 */
@Slf4j
@Service
public class PayServiceImpl implements PayService {

    private final WxPayService wxPayService;
    private final OrderService orderService;

    @Autowired
    public PayServiceImpl(WxPayService wxPayService, OrderService orderService) {
        this.wxPayService = wxPayService;
        this.orderService = orderService;
    }


    @Override
    public WxPayMpOrderResult create(OrderDTO dto) {
        //判断是否已支付
        if (dto.getPayStatus() == 1) {
            throw new SellException(ResultEnum.ORDER_YES_PAID);
        }
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        request.setBody("啦啦啦订单");
        request.setOutTradeNo(dto.getOrderId());
        request.setTotalFee(WxPayBaseRequest.yuanToFee(dto.getOrderAmount().toString()));
        request.setSpbillCreateIp("8.8.8.8");
        request.setOpenid(dto.getBuyerOpenid());
        WxPayMpOrderResult result;
        log.info("[微信][发起支付],Request:{}", request);
        try {
            result = wxPayService.createOrder(request);
            log.info("[微信][发起支付],Response:{}", request);
        } catch (WxPayException e) {
            log.error("微信支付失败！订单号：{},原因:{}", dto.getOrderId(), e.getMessage());
            throw new SellException(ResultEnum.ORDER_PAY_ERROR);
        }
        return result;

    }

    @Override
    public WxPayOrderNotifyResult notify(String notifyData) {
        WxPayOrderNotifyResult wxPayOrderNotifyResult;
        try {
            wxPayOrderNotifyResult = wxPayService.parseOrderNotifyResult(notifyData);
        } catch (WxPayException e) {
            log.error("微信异步通知解析失败！原因:{}", e.getMessage());
            throw new SellException(ResultEnum.PAY_AJAX_ERROR);
        }
        //修改订单状态
        String openid = wxPayOrderNotifyResult.getOpenid();
        String orderId = wxPayOrderNotifyResult.getOutTradeNo();
        log.info("[微信][异步通知]订单号:{},用户openid：{}",orderId,openid);
        orderService.paid(wxPayOrderNotifyResult,orderId);
        return wxPayOrderNotifyResult;
    }


}
