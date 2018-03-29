package shop.warscat.sell.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import shop.warscat.sell.config.ProjectUrlConfig;
import shop.warscat.sell.exception.SellerAuthorizeException;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-29
 * Time: 17:56
 */
@Aspect
@Component
public class SellAuthorizeAspect {

    @Pointcut("execution(public * shop.warscat.sell.controller.Seller*.*(..))"
            +"&& !execution(public * shop.warscat.sell.controller.SellerController.*(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpSession session = request.getSession();
        if (session.getAttribute("token")==null || !session.getAttribute("token").equals("token")) {
            throw new SellerAuthorizeException();
        }
    }
}
