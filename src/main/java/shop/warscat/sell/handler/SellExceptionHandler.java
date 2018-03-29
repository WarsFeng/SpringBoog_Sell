package shop.warscat.sell.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import shop.warscat.sell.config.ProjectUrlConfig;
import shop.warscat.sell.exception.SellerAuthorizeException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-29
 * Time: 18:27
 */
@ControllerAdvice
public class SellExceptionHandler {

    private final ProjectUrlConfig projectUrlConfig;

    @Autowired
    public SellExceptionHandler(ProjectUrlConfig projectUrlConfig) {
        this.projectUrlConfig = projectUrlConfig;
    }

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizerException(){
        return new ModelAndView("redirect:".concat(projectUrlConfig.getSell()).concat("/sell/seller/login"));
    }
}
