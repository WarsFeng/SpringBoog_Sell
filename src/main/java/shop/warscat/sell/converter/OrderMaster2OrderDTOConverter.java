package shop.warscat.sell.converter;

import org.springframework.beans.BeanUtils;
import shop.warscat.sell.dto.OrderDTO;
import shop.warscat.sell.model.OrderMaster;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wars
 * Date: 2018-03-26
 * Time: 09:27
 */

public class OrderMaster2OrderDTOConverter {

    public static List<OrderDTO> converter(List<OrderMaster> orderList) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrderMaster order : orderList) {
            OrderDTO dto = new OrderDTO();
            BeanUtils.copyProperties(order, dto);
            //TODO 商品详情List
            orderDTOList.add(dto);
        }
        return orderDTOList;
    }
}
