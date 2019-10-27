package com.zhanglinchun.sell.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * http返回给调用者结果对象
 */
@Data
// 不序列化为空的属性
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {

    // 错误码.
    private Integer code;
    // 提示信息.
    private String msg;
    // 具体内容.
    private T data;
}
