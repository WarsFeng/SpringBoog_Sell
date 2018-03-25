package shop.warscat.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.warscat.sell.service.OrderService;
import shop.warscat.sell.service.PayService;

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
    public String create(@RequestParam("orderId")String orderId,@RequestParam("returnUrl")String returnUrl){
        //校验订单是否存在
        payService.create(orderService.findOneById(orderId));
        return null;
    }
}
