package com.des;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util {
    public static void main(String[] args) {
        String s = "hello world";

        String encode = encode(s);
        System.out.println(encode);

        String result = decode(encode);
        System.out.println(result);


    }

    /**
     * 编码
     * @param source
     * @return
     */
    public static String encode(String source) {
        String result = null;
        try {
            byte[] bytes = source.getBytes("UTF-8");
            result = encode(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 编码
     * @param bytes
     * @return
     */
    public static String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 解码
     * @param source
     * @return
     */
    public static String decode(String source) {
        String result = null;
        try {
            result = new String(Base64.getDecoder().decode(source), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
