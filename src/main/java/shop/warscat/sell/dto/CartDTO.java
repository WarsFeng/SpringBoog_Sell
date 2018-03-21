package shop.warscat.sell.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description: 购物车
 * User: wars
 * Date: 2018-03-21
 * Time: 17:32
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantiry;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantiry) {

        this.productId = productId;
        this.productQuantiry = productQuantiry;
    }
}
