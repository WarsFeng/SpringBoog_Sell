package shop.warscat.sell.service.impl;

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
    @Autowired
    public PayServiceImpl(WxPayService wxPayService) {
        this.wxPayService = wxPayService;
    }


    @Override
    public boolean create(OrderDTO dto) {
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
        try {
            Object order = wxPayService.createOrder(request);
//            Result.ok
        } catch (WxPayException e) {
            log.error("微信支付失败！订单号：{},原因:{}",dto.getOrderId(),e.getMessage());
            return false;
        }
        return true;

    }


}
