package shop.warscat.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import shop.warscat.sell.converter.OrderMaster2OrderDTOConverter;
import shop.warscat.sell.dto.OrderDTO;
import shop.warscat.sell.enums.ResultEnum;
import shop.warscat.sell.exception.SellException;
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
     *
     * @param page 页数
     * @param size 每页项数
     * @return 结果
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size, Map<String, Object> map) {
        Page<OrderMaster> orderPage = orderService.findList(PageRequest.of(page - 1, size, new Sort(Sort.Direction.DESC, "createTime")));
        PageImpl<OrderDTO> dtoPage = new PageImpl<>(OrderMaster2OrderDTOConverter.converter(orderPage.getContent()));
        map.put("orderDTOPage", dtoPage);
        map.put("totalPage", orderPage.getTotalPages());
        map.put("curentPage", page);
        map.put("pageSize", size);
        return new ModelAndView("order/list", map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId, Map<String, Object> map) {
        try {
            Boolean result = orderService.adminCancel(orderId);
            log.info("[取消订单][{}]订单id{}", result, orderId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.SUCCES.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/succes", map);
    }

    /**
     * 订单详情查询
     *
     * @param orderId 订单号
     * @param map     orderDTO
     * @return order/detail
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId, Map<String, Object> map) {
        OrderDTO orderDTO;
        try {
            orderDTO = orderService.findOneById(orderId);
        } catch (SellException e) {
            log.error("[订单详情][查询]错误:{}",e.getMessage());
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("orderDTO", orderDTO);
        return new ModelAndView("order/detail", map);
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,Map<String,Object> map){
        try {
            orderService.adminFinish(orderId);
        } catch (SellException e) {
            log.error("[订单详情][完结订单]错误{}",e.getMessage());
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.FINISH.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/succes", map);
    }
}
