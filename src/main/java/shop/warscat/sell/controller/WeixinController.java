package shop.warscat.sell.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-22
 * Time: 23:39
 */
@Slf4j
@RestController
@RequestMapping("/weixin")
public class WeixinController {


    //授权后回调地址
    /*引导关注者打开授权链接：
    * https://open.weixin.qq.com/connect/oauth2/authorize?
    * appid=wx92bce8a900fc74bb
    * &redirect_uri=http%3a%2f%2fwarscat.xyz%2fsell%2fweixin%2fauth
    * &response_type=code
    * &scope=snsapi_base
    * &state=STATE#wechat_redirect
    * http%3a%2f%2fy39qa4.natappfree.cc%2fsell%2fweixin%2fauth
    * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx92bce8a900fc74bb&redirect_uri=http%3a%2f%2fy39qa4.natappfree.cc%2fsell%2fweixin%2fauth&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect
    * 用户点击授权链接返回一个CODE：
    * code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
    *
    * 使用获取token：
    * https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx92bce8a900fc74bb&secret=SECRET&code=CODE&grant_type=authorization_code
    * 返回：Json数据
    * { "access_token":"ACCESS_TOKEN",   网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
    * "expires_in":7200,                 access_token接口调用凭证超时时间，单位（秒）
    * "refresh_token":"REFRESH_TOKEN",   用户刷新access_token
    * "openid":"OPENID",
    * "scope":"SCOPE" }                  用户授权的作用域，使用逗号（,）分隔
    * 然后就获取到OPENID了
    * */
    @GetMapping("/auth")
    public void auth(@RequestParam("code")String code){
        log.info("进入auth方法,Code:{}",code);
        //关注者打开授权链接之后会返回code，code加入一下url访问后获取token
        String getTokenURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx92bce8a900fc74bb&secret=SECRET&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String token = restTemplate.getForObject(getTokenURL, String.class);
    }

    //测试号token验证
    @RequestMapping("/token")
    public String token(@RequestParam("echostr") String s) {
        if (s.equals("apiapiapi")) {
            return s;
        }
        return null;
    }
}
