package shop.warscat.sell.controller;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.warscat.sell.service.OrderService;
import shop.warscat.sell.service.PayService;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 支付Controller
 * User: wars
 * Date: 2018-03-24
 * Time: 18:16
 */
@Slf4j
@RestController
@RequestMapping("/pay")
public class PayController {

    private final OrderService orderService;
    private final PayService payService;

    @Autowired
    public PayController(OrderService orderService, PayService payService) {
        this.orderService = orderService;
        this.payService = payService;
    }

    @RequestMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId
            , @RequestParam("returnUrl") String returnUrl
            , Map<String, Object> map) {
        //校验订单是否存在
        WxPayMpOrderResult result = payService.create(orderService.findOneById(orderId));
        map.put("map", result);
        map.put("returnUrl", returnUrl);
        return new ModelAndView("pay/create", map);
    }


    /**
     * 微信异步通知
     * @param notifyData 微信传入数据
     * @return 成功页面
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        WxPayOrderNotifyResult notifyResult = payService.notify(notifyData);
        return new ModelAndView("pay/success");
    }
}
