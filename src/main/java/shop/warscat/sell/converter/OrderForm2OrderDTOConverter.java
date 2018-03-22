package shop.warscat.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.GsonJsonParser;
import shop.warscat.sell.dto.CartDTO;
import shop.warscat.sell.dto.OrderDTO;
import shop.warscat.sell.enums.ResultEnum;
import shop.warscat.sell.exception.SellException;
import shop.warscat.sell.form.OrderForm;
import shop.warscat.sell.model.OrderDetail;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 转换
 * User: wars
 * Date: 2018-03-22
 * Time: 13:39
 */
@Slf4j
public class OrderForm2OrderDTOConverter {


    public static OrderDTO converter(OrderForm orderForm){
        OrderDTO dto = new OrderDTO();

        dto.setBuyerName(orderForm.getName());
        dto.setBuyerPhone(orderForm.getPhone());
        dto.setBuyerAddress(orderForm.getAddress());
        dto.setBuyerOpenid(orderForm.getOpenid());
        //GJson转换json数据
        Gson gson = new Gson();
        List<OrderDetail> orderDetailList;
        try {
             orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (SellException e) {
            log.error("[订单Json转换出错]Json{}",orderForm.getItems());
            throw new SellException(ResultEnum.JSON_CONVERTER_ERROR);
        }
        dto.setOrderDetailList(orderDetailList);

        return dto;
    }
}
