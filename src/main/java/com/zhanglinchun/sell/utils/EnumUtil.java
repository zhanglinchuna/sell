package com.zhanglinchun.sell.utils;

import com.zhanglinchun.sell.enums.CodeEnum;

public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getEnumCode())) {
                return each;
            }
        }
        return null;
    }
}
