package shop.warscat.sell.utils;

import shop.warscat.sell.VO.ResultVO;

/**
 * Created with IntelliJ IDEA.
 * Description: 结果集工具
 * User: wars
 * Date: 2018-03-21
 * Time: 03:17
 */

public class ResultVOUtils {

    public static ResultVO success(Object o){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(o);
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
