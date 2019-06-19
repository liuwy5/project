package com.restcontroller;

import com.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api")
public class IpRestController {

    private final static Logger logger = LoggerFactory.getLogger(IpRestController.class);

    @RequestMapping("/ip/simple")
    @ResponseBody
    public String getIpSimple() {
        return IpUtil.obtainIpSimple();
    }

    @RequestMapping("/ip/obtain")
    public String getIp() {
        return IpUtil.obtainIp();
    }
}
