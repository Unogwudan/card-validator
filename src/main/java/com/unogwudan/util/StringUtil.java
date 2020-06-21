package com.unogwudan.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;

public class StringUtil {
    /**
     * Replace all error message place-holders with actual values.
     */
    public static String doStringReplacements(String message, Map<String, String> attributes) {
        for (Map.Entry<String, String> value : attributes.entrySet()) {
            String key = ":" + value.getKey();
            message = message.replaceAll(key, value.getValue());
        }
        return message.replaceAll("@optional", attributes.getOrDefault("@optional",""));
    }

    public static String urlSafe(String url) {
        try {
            return URLEncoder.encode( url, "UTF-8" );
        } catch (UnsupportedEncodingException e) {
            return "Issue while encoding" +e.getMessage();
        }
    }

}
