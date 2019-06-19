package com.controller;

import com.annotation.ZkConfig;
import com.annotation.ZkValue;
import com.util.ZkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/api/zk")
@ZkConfig
public class ZkController implements Serializable{
    @Autowired
    private ZkUtil zkUtil;

    @ZkValue("/username")
    private String username;

    @RequestMapping("/get")
    public String get(String path) {
        return zkUtil.get(path);
    }

    @RequestMapping("/user")
    public String getUsername() {
        return username;
    }
}
