package com.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.cloud.context.config.annotation.RefreshScope;

//@RestController
//@RefreshScope
//@RequestMapping("/zk")
public class ZkUtil {
//    @Value("${username}")
    private String username;

    @RequestMapping("/name")
    public String test() {
        return username;
    }
}
