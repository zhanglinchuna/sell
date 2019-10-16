package com.zhanglinchun.sell.vo;

import lombok.Data;

/**
 * http返回给调用者结果对象
 */
@Data
public class ResultVO<T> {

    // 错误码.
    private Integer code;
    // 提示信息.
    private String msg;
    // 具体内容.
    private T data;
}
