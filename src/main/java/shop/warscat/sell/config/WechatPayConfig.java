package shop.warscat.sell.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description: 微信支付Config
 * User: wars
 * Date: 2018-03-24
 * Time: 19:29
 */

@Component
public class WechatPayConfig {

    private final WechatAccountConfig wechatAccountConfig;
    @Autowired
    public WechatPayConfig(WechatAccountConfig wechatAccountConfig) {
        this.wechatAccountConfig = wechatAccountConfig;
    }

    @Bean
    public WxPayService bestPayService(){
        WxPayService bestPayService = new WxPayServiceImpl();
        bestPayService.setConfig(wxPayConfig());
        return bestPayService;
    }
    @Bean
    public WxPayConfig wxPayConfig(){
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(wechatAccountConfig.getMpAppId());
        wxPayConfig.setMchId(wechatAccountConfig.getMchId());
        wxPayConfig.setMchKey(wechatAccountConfig.getMchKey());
        wxPayConfig.setNotifyUrl(wechatAccountConfig.getNotifyUrl());
        wxPayConfig.setTradeType("JSAPI");
        return wxPayConfig;
    }
}
