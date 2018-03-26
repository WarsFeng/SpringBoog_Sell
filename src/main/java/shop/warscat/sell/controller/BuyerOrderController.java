package shop.warscat.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.warscat.sell.converter.OrderForm2OrderDTOConverter;
import shop.warscat.sell.dto.OrderDTO;
import shop.warscat.sell.enums.ResultEnum;
import shop.warscat.sell.exception.SellException;
import shop.warscat.sell.form.OrderForm;
import shop.warscat.sell.service.OrderService;
import shop.warscat.sell.utils.ResultVOUtils;
import shop.warscat.sell.vo.ResultVO;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

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
    public ResultVO create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确,orderForm{}", orderForm);
            throw new SellException(ResultEnum.RARAM_ERROR.getCode()
                    , bindingResult.getFieldError().getDefaultMessage());
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


        HashMap<String, String> data = new HashMap<>();
        data.put("orderId", orderId);
        return ResultVOUtils.success(data);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO list(@RequestParam("openid") String openid
            , @RequestParam(value = "page", defaultValue = "0") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("[买家openId不能为空]OpenId:{}", openid);
            throw new SellException(ResultEnum.OPENID_EMTRY);
        }
        Page<OrderDTO> list = service.findListByOpenid(openid, PageRequest.of(page, size));
        List<OrderDTO> orderDTOList = list.getContent();
        System.out.println(orderDTOList.get(0).getUpdateTime());
        System.out.println();
        return ResultVOUtils.success(orderDTOList);
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO detail(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = service.findOne(openid, orderId);
        return ResultVOUtils.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        if (service.cancel(openid, orderId)) {
            return ResultVOUtils.success();
        } else {
            return ResultVOUtils.error(1,"失败");
        }
    }

    //完结订单
    @PostMapping("/finish")
    public ResultVO finish(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId){
        if (service.finish(openid, orderId)){
            return ResultVOUtils.success();
        } else {
            return ResultVOUtils.error(1,"失败");
        }
    }

}
