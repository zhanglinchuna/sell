package com.zhanglinchun.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;

@Data
public class ProductForm {
    // 商品id
    private String productId;
    // 名字.
    @NotEmpty(message = "商品名称不能为空")
    private String productName;
    // 单价.
    @NotEmpty(message = "商品价格不能为空")
    private BigDecimal productPrice;
    // 库存.
    @NotEmpty(message = "商品库存不能为空")
    private Integer productStock;
    // 描述.
    private String productDescription;
    // 小图.
    private String productIcon;
    // 类目编号.
    @NotEmpty(message = "商品类目编号不能为空")
    private Integer categoryType;
}
