package com.vo;

public class Result {
    private String code;

    private String resp;

    public Result(String code, String resp) {
        this.code = code;
        this.resp = resp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }
}
