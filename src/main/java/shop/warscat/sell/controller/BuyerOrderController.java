package shop.warscat.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.warscat.sell.converter.OrderForm2OrderDTOConverter;
import shop.warscat.sell.dto.OrderDTO;
import shop.warscat.sell.enums.ResultEnum;
import shop.warscat.sell.exception.SellException;
import shop.warscat.sell.form.OrderForm;
import shop.warscat.sell.service.OrderService;
import shop.warscat.sell.vo.ResultVO;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 订单
 * User: wars
 * Date: 2018-03-22
 * Time: 09:38
 */

@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    private final OrderService service;
    @Autowired
    public BuyerOrderController(OrderService service) {
        this.service = service;
    }

    //创建订单
    @PostMapping("/create")
    @Transactional
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确,orderForm{}" ,orderForm);
            throw new SellException(ResultEnum.RARAM_ERROR.getCode()
                    ,bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO dto = OrderForm2OrderDTOConverter.converter(orderForm);
        if (CollectionUtils.isEmpty(dto.getOrderDetailList())) {
            log.error("[创建订单为空]购物车不能为空");
            throw new SellException(ResultEnum.ORDER_DATAIL_EMTRY);
        }
        String orderId = service.create(dto);
        if (orderId == null) {
            throw new SellException(ResultEnum.ORDER_CREATE_ERROR);
        }

        ResultVO<Map<String, String>> mapResultVO = new ResultVO<>();

        mapResultVO.setCode(0);
        mapResultVO.setMsg("成功");
        HashMap<String, String> data = new HashMap<>();
        data.put("orderId",orderId);
        mapResultVO.setData(data);
        return mapResultVO;
    }

    //订单列表

    //订单详情


}
