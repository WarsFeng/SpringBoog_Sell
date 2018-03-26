package shop.warscat.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import shop.warscat.sell.converter.OrderMaster2OrderDTOConverter;
import shop.warscat.sell.dto.OrderDTO;
import shop.warscat.sell.model.OrderMaster;
import shop.warscat.sell.service.OrderService;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 卖家端订单
 * User: wars
 * Date: 2018-03-26
 * Time: 12:03
 */

@Slf4j
@RestController
@RequestMapping("/seller/order")
public class SellerOrderController {

    private final OrderService orderService;

    @Autowired
    public SellerOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 分页查询所有订单
     * @param page 页数
     * @param size 每页项数
     * @return 结果
     */
    @RequestMapping("list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page
            , @RequestParam(value = "size",defaultValue = "10") Integer size, Map<String,Object> map) {
        Page<OrderMaster> orderPage = orderService.findList(PageRequest.of(page-1, size));
        PageImpl<OrderDTO> dtoPage = new PageImpl<>(OrderMaster2OrderDTOConverter.converter(orderPage.getContent()));
        map.put("orderDTOPage",dtoPage);
        map.put("totalPage",orderPage.getTotalPages());
        map.put("curentPage",page);
        map.put("pageSize",size);
        return new ModelAndView("order/list",map);
    }
}
