package shop.warscat.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.warscat.sell.service.SellerService;
import shop.warscat.sell.utils.KeyUtils;
import shop.warscat.sell.utils.MessageUtils;

import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-29
 * Time: 16:45
 */
@Slf4j
@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("username") String username
    , @RequestParam("password") String password, HttpSession session, Map<String,Object> map){
        if (sellerService.login(username,password)) {
            session.setAttribute("token","token");
            session.setMaxInactiveInterval(-1);
            map.put("msg","登录成功!");
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/succes",map);
        }
        map.put("msg","登录失败!");
        map.put("url","/sell/seller/login");
        return new ModelAndView("common/error",map);
    }

    /**
     * 新订单消息通知,客户端定时Ajax调用此方法
     * @return 1为有新消息,0无,
     */
    @PostMapping("/message")
    @ResponseBody
    public Integer message(){
        Integer messageCode = MessageUtils.getMessageCode();
        if (messageCode >0) {
            MessageUtils.lessMessage();
            return messageCode;
        }
        return 0;
    }
}
