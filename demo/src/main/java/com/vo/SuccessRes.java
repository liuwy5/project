package com.vo;

public class SuccessRes extends Result {
    public SuccessRes() {
        this("0000", "操作成功");
    }

    public SuccessRes(String resp) {
        this("0000", resp);
    }

    public SuccessRes(String code, String resp) {
        super(code, resp);
    }
}
