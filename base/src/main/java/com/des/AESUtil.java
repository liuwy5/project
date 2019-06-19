package com.des;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AESUtil {
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding"; // 默认的加密算法

    public static String encrypt(String source, String password) {
        String result = null;
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM); // 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));
            byte[] bytes = cipher.doFinal(source.getBytes("UTF-8"));
            result = Base64.getEncoder().encodeToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String decrypt(String source, String password) {
        String result = null;
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));
            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(source));
            result = new String(bytes, "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 生成加密密钥
     * @param password
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);

            // SecureRandom 实现完全随操作系统本身的內部状态，除非调用方在调用 getInstance 方法，然后调用 setSeed 方法
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            keyGenerator.init(128, secureRandom);

            SecretKey secretKey = keyGenerator.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM); // 转换为AES专用密钥
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String source = "hello world我们";
        String password = "123456";
        String encryptStr = encrypt(source, password);
        System.out.println(encryptStr);
        System.out.println(decrypt(encryptStr, password));
    }
}
