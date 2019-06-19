package com.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class HttpUtil {
    public static void send(String url, MultiValueMap<String, String> params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
        String strbody=restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
        System.out.println("result: " + strbody);
    }

    public static void sendGet(String url, MultiValueMap<String, String> params) {

    }

    public static void sendPost(String url, MultiValueMap<String, String> params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        RestTemplate restTemplate=new RestTemplate();

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
        String strbody=restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
        System.out.println("result: " + strbody);
    }

    public static MultiValueMap<String, String> param() {
        String appId = "98ae69";
        String appKey = "xq7mpyotuw";
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        params.add("account", appId);
        params.add("password", Md5Util.md5Upper(appKey));
        params.add("mobile", "13067977672");
        params.add("content", "您的短信验证码是43232如非本人操作，请忽略此短信。本短信免费。【畅卓科技】");
        return params;
    }

    public static void main(String[] args) {
        String url = "http://api.chanzor.com/send";
        MultiValueMap<String, String> params = param();
        sendPost(url, params);
    }
}
