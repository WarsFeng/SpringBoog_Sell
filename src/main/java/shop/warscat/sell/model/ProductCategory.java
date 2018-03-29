package shop.warscat.sell.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 商品分类
 * User: wars
 * Date: 2018-03-20
 * Time: 22:23
 */

@Data
@Entity
@DynamicUpdate
public class ProductCategory {

    /**商品分类id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /**商品分类名称*/
    private String categoryName;

    /**商品分类类型*/
    private Integer categoryType;

    /**数据创建时间*/
    private Date createTime;

    /**数据更新时间*/
    private Date updateTime;

}
