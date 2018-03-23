package shop.warscat.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description: 工程Url配置
 * User: wars
 * Date: 2018-03-23
 * Time: 17:10
 */
@Data
@Component
@ConfigurationProperties(prefix = "projecturl")
public class ProjectUrlConfig {

    private String wechatMpAuthorize;
    private String wechatOpenAuthorize;
    private String sell;

}
