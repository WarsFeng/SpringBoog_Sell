package shop.warscat.sell.service;

import shop.warscat.sell.dto.OrderDTO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-24
 * Time: 18:56
 */

public interface PayService {

    public boolean create(OrderDTO dto);
}
