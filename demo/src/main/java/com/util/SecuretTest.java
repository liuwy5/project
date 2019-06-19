package com.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.alibaba.druid.sql.visitor.functions.If;

import java.io.File;

public class SecuretTest {

//    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMqU0/OC1PXBYhs3WpA5XDS1XLPNe238YG1a9aEbng9yPm8NQS0q8ZIU/EyT3QXnvMZ2uEv5/5VmjKjDd0zIzxoBS23e/yXG7DpqUBDvXazaRQYzxLcvg9+yEBQeQMYblwDo6GiP3KN/T21RC1EV8pa+NtLs5ee4C2wgnHxaN9wwIDAQAB";
//    private static final String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAIypTT84LU9cFiGzdakDlcNLVcs817bfxgbVr1oRueD3I+bw1BLSrxkhT8TJPdBee8xna4S/n/lWaMqMN3TMjPGgFLbd7/JcbsOmpQEO9drNpFBjPEty+D37IQFB5AxhuXAOjoaI/co39PbVELURXylr420uzl57gLbCCcfFo33DAgMBAAECgYEAiAVkFMFvV3HWWQqguiskVPFx6pJQi6CDb7KN2kyP8lMrXbT/b6BbALMs30A3zVrA3p4X5AwmV8hOl6fWLNVtaPEBxrHsvXE+dlxm5VeloA7I/qH6NEiPgHazCviXh2OEleCvCX636LQuZGcTsRxrOqfMLblS2RFwejRY01xLgqECQQD8UbiKch3taiCq7EUrxa65emB59YfCYO/IQNQ09s52xifWI2hGXeAzUXZbvF2vtf1LW4sHIAFGp1mqtxjDKO0zAkEAjraY/FWrC6kC7s50gAk4kjTct0TKx61msXEQcMitRu7EuSCwzvD66ByZHNiORODeM7Tr0LnED84dfqft5TONMQJAPVipI5AHUrfg5W24i6Huj03/sudNcKcc5XVNour2283l1ZyaN7GPVyrROhCcPqJiyTWiocLYTJNXq7gEnz6OHQJBAIdVWRZRFvbju0hCt++Czwa24mdY8QMRyMST94c8hjUxz5PPEFvt4cGi/P+BzMg8hfp6ovU4dFiYmRe2n74ueMECQQD1BELz5kVYCX3Ne8Ap6e/zAde0vGRfRqfXSBqKC5l3lmbQf5e/EcUnif76tytXHtzh6j2lY5HvGshCBqfFov9G";

    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqTb12ixrHhxaxWAI5EKWXyVPlyb1QsCiNFyKfHUBOU8fZI1IJ0K5V0pOEuANB3prfc/JKhqoIxShJpyXWGbqQ8mE6oXddLX5Uo0pCnoOmCovm6oJWIWrwVByR0wFOOQfRoLB4lvxvk36qLpgTVFsBRP7jVdPOI8Oh4PY/1ZoYRA/R1s+q/aNRWCsoe3gNZARNJMhgKr8IujMHxjtbEmkOkNEtqI653QNi8WFReGejkgpTzjQUgnkroPnYI5T5EDXcvauZhqOsMihI3lYUYZD5JqljLJ5+eKr/lHZMwfGCR0PCQjLrf9pcgO0JeSLSYF2IHF3hcrv5KMe0+mp5n59swIDAQAB";
    private static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCpNvXaLGseHFrFYAjkQpZfJU+XJvVCwKI0XIp8dQE5Tx9kjUgnQrlXSk4S4A0Hemt9z8kqGqgjFKEmnJdYZupDyYTqhd10tflSjSkKeg6YKi+bqglYhavBUHJHTAU45B9GgsHiW/G+TfqoumBNUWwFE/uNV084jw6Hg9j/VmhhED9HWz6r9o1FYKyh7eA1kBE0kyGAqvwi6MwfGO1sSaQ6Q0S2ojrndA2LxYVF4Z6OSClPONBSCeSug+dgjlPkQNdy9q5mGo6wyKEjeVhRhkPkmqWMsnn54qv+UdkzB8YJHQ8JCMut/2lyA7Ql5ItJgXYgcXeFyu/kox7T6anmfn2zAgMBAAECggEALIdX/we9UHkpsdLpxBd5tqP848vOsrg6dwj7MHEh11Do8+7x09MNLtlQ3kqzYwpmCv+I8ll9cW3WUnDWqt66Owhmv8hmVHYmnAClkio4ePZw/MesGe1jG0XuLLDmlugBCu8R0l1bqjafBZO2D+KjSFrP0YvuPvX2XtblXZwBb7kE9tGZCrQJjIwKCwFHaPei5F04WTfw4ZZDDVqlQYssuQIlefg6k/SGQ2vaiDtUZ0dSDVow18lKZmzh3RGe9/C0lFn1E1nQgkfZDYKeuNvNAywT1dENO/faMeKmMBNVs4Cu+HNQjX4X/pui6B2Kzp4dgDBqwgROUQhVdKrlv1xLoQKBgQD/XUklwcBhLOJ0/ALSuhf4nWkieL0BmZjdnS7kIy2gcp8P1lkl+Q7XeFI4JurKnikXSDf5bXML2lYoOM5Eu4OvZbxqfO5HkOUfb42HNfa5UsrT+4eDyutppQkk191H21SngO+d5jhl90V5VYJHQTtgDHdyNKKqvuKl6d69EHzQywKBgQCposgHGPvkEFW+apTzgGlX2w8WmMX1K3sl4aIIoS/9qVEpccp722Du01KOWCU4iq5xpH6ninRRKJ9E/V0E8/VZI9eIBoVu4B0NTjb7xvEoUUD5eChGLXcfh1FQHlefPSfQlK6N3lxDU/nZvN9EtngyqcEJDSBPCxytF0gx8iZxuQKBgFpzA06xUt/YkxmWjbVwHr97mdqQ43lmmyOb4hkCcM3M65Qjew4dKmmdf5nOBwnffXR2qwTZBI/lNPEoNb9Yn6sHoPYi7A9DPjSlQX7r2UkJDziIdhZzUHDp+NQo9YLXKCPwYm8ONVnAfMYeSHfOniZGJCS6igdqd0MHFFLpPRyDAoGBAKMm5Kuu49VdwJn7eHcaMjdFgSWCmiJLR44PjQAvxzB5AI6T2uN1A62vod8199aBy3WReB87ktPb9hMse6Kf4IFzb4BshTk2Y7rAVzGhDaBxRcSqRFH6uG+ifhpIlI2bDAgYeCoKi0JWKh+5ACPeO4y+p+JEtaZQUaxIkC85brBBAoGAWyL7djKZtKgQMZUYnnNmd/izoZQCsqx1Tqi+ZXAnJDWdwYbNFBGk0hnD+7+94Z/RGWU7AR3A4atrqR1FlUAR1c0X2MBZTg2j4LEn+BE93qfY9924w60znQBMYDIPOzYjx00uXeXBqhQTlf2lOZlfh5GABkjm3g23UjZr4Y7H3C8=";


    public static void main(String[] args) {
        String source = "Song1357";
        String result = encrypt(source);
        System.out.println(result);
//        result = "L/baOPxpdVtXMtKpVnwVe95UKXLszwpNS3y6kAbqq9zhnvqymuzaFgQu0orgptyiuvUvTjl0iqrMxtE1XW92gEwH79r5jjauyeDTYmDlMrjRSxY1OvZHUV2zUkpVgfgtubnni6eM0s05rRa+2ZkaFlSTXwS7BPL/EcM3a9Oh3rVXT/L6MQsIPcj5+j4cSRJIr+nfkV9rCagLuwzWsMRrWR3+50Z7VGgPUsBpy/xS7mv0dSdBOmOmhf4zDyVy3zbvmuDm63eNeKX7QhoVAevui7hjfnyLuMQ8P5X6Lk9atoc/+4JF3Yx87e5R0g/KIJZqaTR4pGhze2s8oPQ9Xsf2Tg==";
        String res = decrypt(result);
        System.out.println(res);

        String s1 = "mpphhMIUwDtH489kJ84MP0hlyY/prJx 3DFs4uQcFV6qdh8DuDf2XVdnQ1A1JPOTNRt0iuag hT2GDh032zZ9j3DClTko8jrpyNuq6foPTneVNbEPBmKR5Cc/t1r988C Tnx9nRLYsD7pfZ2dHlVt3t86aCm7ZdEpA7DVjmZmhjzBX/tIndoQ2NuQUn2MoLJzcFB5OY768 PWZIKnq9o69qlRPkQqvkfFZUEceYtoQd0HCbvzhLaWb CQq6RMSQJp650LJrVNMyckPtciIvWoRKuW9 DE7JJLPADrZTNxqYbKsuRrpwaXfaOeOB1s/Heduqc4rPK4r1 aUaYlpEr4A==";
        if (s1.contains(" ")) {
            s1 = s1.replaceAll(" ", "+");
            System.out.println(s1);
        }

        String s = "fdsfds/r4343/ffdd.jpg";
        int index = s.lastIndexOf(".");
        String s11 = s.substring(0, index) + "_" + s.substring(index + 1);
        System.out.println(s11);
    }

    public static String encrypt(String source) {
        return SecureUtil.rsa(null, PUBLIC_KEY).encryptBase64(source, KeyType.PublicKey);
    }

    public static String decrypt(String source) {
        return SecureUtil.rsa(PRIVATE_KEY, null).decryptStr(source, KeyType.PrivateKey);
    }
}
