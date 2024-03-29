package com.zhanglinchun.sell.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum<Integer> {

    NEW(0,"新订单"),
    FINISHED(1,"已结算"),
    CANCEL(2,"已取消");

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getEnumCode() {
        return this.code;
    }
}
