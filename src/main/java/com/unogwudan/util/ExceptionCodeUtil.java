package com.unogwudan.util;

import com.unogwudan.constant.ExceptionKeyConstant;

import java.util.Map;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
public class ExceptionCodeUtil {
    private static Map<String, String> map = new MapBuilder<String, String>()
            .a(ExceptionKeyConstant.SUCCESS, "00")
            .a(ExceptionKeyConstant.NOT_FOUND, "404")
            .a(ExceptionKeyConstant.INTERNAL_SERVER_ERROR, "500")

            // Session Error Codes

            .get();


    public static String msg(String code) {
        return PropertyUtil.msg(code);
    }

    public static String code(String code) {
        return map.get(code);
    }

    public static KeyValue data(String code) {
        return new KeyValue(map.get(code), PropertyUtil.msg(code));
    }
}
