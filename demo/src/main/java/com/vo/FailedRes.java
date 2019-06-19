package com.vo;

public class FailedRes extends Result {
    public FailedRes(String resp) {
        super("1001", resp);
    }
    public FailedRes(String code, String resp) {
        super(code, resp);
    }
}
