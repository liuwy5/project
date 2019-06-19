package com.des;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class RSAUtil {

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMqU0/OC1PXBYhs3WpA5XDS1XLPNe238YG1a9aEbng9yPm8NQS0q8ZIU/EyT3QXnvMZ2uEv5/5VmjKjDd0zIzxoBS23e/yXG7DpqUBDvXazaRQYzxLcvg9+yEBQeQMYblwDo6GiP3KN/T21RC1EV8pa+NtLs5ee4C2wgnHxaN9wwIDAQAB";
    private static final String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAIypTT84LU9cFiGzdakDlcNLVcs817bfxgbVr1oRueD3I+bw1BLSrxkhT8TJPdBee8xna4S/n/lWaMqMN3TMjPGgFLbd7/JcbsOmpQEO9drNpFBjPEty+D37IQFB5AxhuXAOjoaI/co39PbVELURXylr420uzl57gLbCCcfFo33DAgMBAAECgYEAiAVkFMFvV3HWWQqguiskVPFx6pJQi6CDb7KN2kyP8lMrXbT/b6BbALMs30A3zVrA3p4X5AwmV8hOl6fWLNVtaPEBxrHsvXE+dlxm5VeloA7I/qH6NEiPgHazCviXh2OEleCvCX636LQuZGcTsRxrOqfMLblS2RFwejRY01xLgqECQQD8UbiKch3taiCq7EUrxa65emB59YfCYO/IQNQ09s52xifWI2hGXeAzUXZbvF2vtf1LW4sHIAFGp1mqtxjDKO0zAkEAjraY/FWrC6kC7s50gAk4kjTct0TKx61msXEQcMitRu7EuSCwzvD66ByZHNiORODeM7Tr0LnED84dfqft5TONMQJAPVipI5AHUrfg5W24i6Huj03/sudNcKcc5XVNour2283l1ZyaN7GPVyrROhCcPqJiyTWiocLYTJNXq7gEnz6OHQJBAIdVWRZRFvbju0hCt++Czwa24mdY8QMRyMST94c8hjUxz5PPEFvt4cGi/P+BzMg8hfp6ovU4dFiYmRe2n74ueMECQQD1BELz5kVYCX3Ne8Ap6e/zAde0vGRfRqfXSBqKC5l3lmbQf5e/EcUnif76tytXHtzh6j2lY5HvGshCBqfFov9G";

    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final int MAX_DECRYPT_BLOCK = 128;

    public static void main(String[] args) {
        keygen();
//        Map<String, String> keyMap = keygen();
//        String publicKey = keyMap.get("publicKey");
//        String privateKey = keyMap.get("privateKey");
//
//        System.out.println(publicKey);
//        System.out.println(privateKey);

//        String source = "hello world";
//        String encryptStr = encrypt(source, CipherAlgorithm.RSA.getValue());
//        System.out.println("encryptStr: ");
//        System.out.println(encryptStr);
//
//        String decryptStr = decrypt(encryptStr, CipherAlgorithm.PKCS1Padding.getValue());
//        System.out.println("decryptStr: ");
//        System.out.println(decryptStr);

//        String encryptByPrivateStr = encryptByPrivate(source, privateKey);
//        System.out.println("encryptByPrivateStr: ");
//        System.out.println(encryptByPrivateStr);
//
//        String decryptByPublicStr = decryptByPublic(encryptByPrivateStr, publicKey);
//        System.out.println("decryptByPublicStr: ");
//        System.out.println(decryptByPublicStr);

//        String signatureStr = sign(source, SignatureCipherAlgorithm.MD5withRSA.getValue());
//        System.out.println("signatureStr: ");
//        System.out.println(signatureStr);
//
//        boolean verifyResult = verifySignature(source, signatureStr, SignatureCipherAlgorithm.MD5withRSA.getValue());
//        System.out.println("verifyResult: " + verifyResult);
    }

    public static void test() {
        String pwd = "";
        String pwd1 = SecureUtil.rsa(PRIVATE_KEY, null).decryptStr(pwd, KeyType.PrivateKey);

    }

    public static PublicKey getPublicKey() {
        PublicKey publicKey = null;
        try {
            publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(
                    Base64.getDecoder().decode(PUBLIC_KEY.getBytes("UTF-8"))));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static PrivateKey getPrivateKey() {
        PrivateKey privateKey = null;
        try {
            privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(
                    Base64.getDecoder().decode(PRIVATE_KEY.getBytes("UTF-8"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    /**
     * 公钥加密
     * @param source
     */
    public static String encrypt(String source, String cipherAlgorithm) {
        String result = null;
        try {
            RSAPublicKey rsaPublicKey = (RSAPublicKey) getPublicKey();

            // RSA加密
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = source.getBytes("UTF-8");
            int length = bytes.length;
            int offset = 0;
            byte[] cache;
            // 对数据分段加密
            while (length - offset > 0) {
                if (length - offset > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(bytes, offset, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(bytes, offset, length - offset);
                }
                byteArrayOutputStream.write(cache, 0, cache.length);
                offset += MAX_ENCRYPT_BLOCK;
            }

            result = Base64Util.encode(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.close();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 私钥解密
     * @param source
     * @return
     */
    public static String decrypt(String source, String cipherAlgorithm) {
        String result = null;
        try {
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) getPrivateKey();

            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = Base64.getDecoder().decode(source.getBytes("UTF-8"));
            int length = bytes.length;
            int offset = 0;
            byte[] cache;
            // 对数据分段加密
            while (length - offset > 0) {
                if (length - offset > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(bytes, offset, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(bytes, offset, length - offset);
                }
                byteArrayOutputStream.write(cache, 0, cache.length);
                offset += MAX_DECRYPT_BLOCK;
            }

            result = new String(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String encryptByPrivate(String source, String privateKey) {
        String result = null;
        try {
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(
                    Base64.getDecoder().decode(privateKey.getBytes("UTF-8"))));

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);

            result = Base64Util.encode(cipher.doFinal(source.getBytes("UTF-8")));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String decryptByPublic(String source, String publicKey) {
        String result = null;
        try {
            RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(
                    Base64.getDecoder().decode(publicKey)));

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);

            result = new String(cipher.doFinal(Base64.getDecoder().decode(source.getBytes("UTF-8"))));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public static String sign(String source) {
        return sign(source, "MD5withRSA");
    }

    public static String sign(String source, String cipherAlgorithm) {
        String signatureStr = null;
        try {
            Signature signature = Signature.getInstance(cipherAlgorithm);
            signature.initSign(getPrivateKey());
            signature.update(source.getBytes("UTF-8"));
            signatureStr = Base64.getEncoder().encodeToString(signature.sign());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signatureStr;
    }

    public static boolean verifySignature(String source, String signatureStr) {
        return verifySignature(source, signatureStr, "MD5withRSA");
    }

    public static boolean verifySignature(String source, String signatureStr, String cipherAlgorithm) {
        boolean verify = false;
        try {
            Signature signature = Signature.getInstance(cipherAlgorithm);
            signature.initVerify(getPublicKey());
            signature.update(source.getBytes("UTF-8"));
            verify = signature.verify(Base64.getDecoder().decode(signatureStr.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verify;
    }

    /**
     * 随机生成密钥对
     */
    public static Map<String, String> keygen() {
        Map<String, String> map = new HashMap<>();
        try {
            // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            // 初始化密钥对生成器，密钥大小为96-1024位
            keyPairGenerator.initialize(1024, new SecureRandom());

            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

            System.out.println(rsaPublicKey.getModulus());
            System.out.println(rsaPublicKey.getModulus().bitLength());
            System.out.println(rsaPublicKey.getPublicExponent());
            System.out.println(rsaPrivateKey.getModulus());
            System.out.println(rsaPrivateKey.getPrivateExponent());
            System.out.println(rsaPrivateKey.getPrivateExponent().bitLength());

            map.put("publicKey", Base64Util.encode(rsaPublicKey.getEncoded()));
            map.put("privateKey", Base64Util.encode(rsaPrivateKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return map;
    }
}

/**
 * signature instance mode
 * Sha1WithRSA MD5withRSA
 */
enum CipherAlgorithm {
    RSA("RSA"),
    PKCS1Padding("RSA/ECB/PKCS1Padding"); // 默认方式

    private String value;

    // 算法名称 反馈模式 填充方案
    private CipherAlgorithm (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

/**
 * signature instance mode
 * Sha1WithRSA MD5withRSA
 */
enum SignatureCipherAlgorithm {
    Sha1WithRSA("Sha1WithRSA"),
    MD5withRSA("MD5withRSA");

    private String value;
    private SignatureCipherAlgorithm (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}