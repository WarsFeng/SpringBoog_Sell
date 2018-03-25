package shop.warscat.sell;

import com.github.binarywang.wxpay.bean.request.WxPayBaseRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-24
 * Time: 22:15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class YuanToFen {

    @Test
    public void demo1(){
        System.out.println(WxPayBaseRequest.yuanToFee(new BigDecimal(12.12).toString()));
    }
}
