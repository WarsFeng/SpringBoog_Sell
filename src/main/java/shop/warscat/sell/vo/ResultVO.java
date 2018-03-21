package shop.warscat.sell.vo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:Http请求返回的最外层对象
 * User: wars
 * Date: 2018-03-21
 * Time: 01:24
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;

}
