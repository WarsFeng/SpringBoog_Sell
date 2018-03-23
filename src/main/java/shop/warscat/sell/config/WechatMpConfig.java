package shop.warscat.sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description: 微信SDK
 * User: wars
 * Date: 2018-03-23
 * Time: 16:36
 */
@Component
public class WechatMpConfig {

    private final WechatAccountConfig wechatAccountConfig;

    @Autowired
    public WechatMpConfig(WechatAccountConfig wechatAccountConfig) {
        this.wechatAccountConfig = wechatAccountConfig;
    }

    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(WxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage WxMpConfigStorage(){
        WxMpConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        ((WxMpInMemoryConfigStorage) wxMpConfigStorage).setAppId(wechatAccountConfig.getMpAppId());
        ((WxMpInMemoryConfigStorage) wxMpConfigStorage).setSecret(wechatAccountConfig.getMpAppSecret());
        return wxMpConfigStorage;
    }

}
