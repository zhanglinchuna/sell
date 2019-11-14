package com.zhanglinchun.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


@Data
public class CategoryForm {

    private Integer categoryId;
    // 类目名字.
    @NotEmpty(message = "类目名称不能为空")
    private String categoryName;
    // 类目编号.
    @NotNull(message = "类目类型不能为空")
    private Integer categoryType;
}
