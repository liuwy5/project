package com.des;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    public static String encodeMd5(String source) {
        byte[] secretBytes = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(source.getBytes());
            secretBytes = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将加密后的数据转换为16进制数字
        String result = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - result.length(); i++) {
            result = "0" + result;
        }
        return result;
    }

    public static void main(String[] args) {
        String source = "hello world";
        System.out.println(encodeMd5(source));
    }
}
