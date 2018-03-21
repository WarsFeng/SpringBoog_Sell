package shop.warscat.sell.dto;

import lombok.Data;
import shop.warscat.sell.model.OrderDetail;
import shop.warscat.sell.model.OrderMaster;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:订单包含订单详情列表
 * User: wars
 * Date: 2018-03-21
 * Time: 14:04
 */

public class OrderDTO extends OrderMaster {
    private List<OrderDetail> orderDetailList;

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
