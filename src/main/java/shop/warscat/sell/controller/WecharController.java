package shop.warscat.sell.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.warscat.sell.config.ProjectUrlConfig;
import shop.warscat.sell.enums.ResultEnum;
import shop.warscat.sell.exception.SellException;

/**
 * Created with IntelliJ IDEA.
 * Description: 微信校验认证
 * User: wars
 * Date: 2018-03-23
 * Time: 13:56
 */

@Slf4j
@Controller
@RequestMapping("/wechat")
public class WecharController {

    private final WxMpService wxMpService;
    private final ProjectUrlConfig projectUrlConfig;

    @Autowired
    public WecharController(WxMpService wxMpService, ProjectUrlConfig projectUrlConfig) {
        this.wxMpService = wxMpService;
        this.projectUrlConfig = projectUrlConfig;
    }

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl
                (projectUrlConfig.getWechatMpAuthorize() + "/sell/wechat/userInfo"
                        , WxConsts.OAuth2Scope.SNSAPI_BASE, URIUtil.encodeURIComponent(returnUrl));
        log.info("[重定向的Url]：{}",redirectUrl);
        log.info("[Return的Url]：{}",returnUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {
        log.info("使用Code获取用户信息 Code：{}", code);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("[微信网页授权] {} ", e);
            throw new SellException(ResultEnum.WX_WEB_ERROR.getCode(), e.getError().getErrorMsg());
        }
        //获取用户OpenId
        String openId = wxMpOAuth2AccessToken.getOpenId();
        String s = "redirect:" + returnUrl + "?openid=" + openId;
        System.out.println(s);
        return s;
    }
}
