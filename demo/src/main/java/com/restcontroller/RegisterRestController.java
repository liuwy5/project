package com.restcontroller;

import com.service.RegisterService;
import com.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rest/api")
public class RegisterRestController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterRestController.class);

    @Resource
    private RegisterService registerService;

    @RequestMapping("/reg")
    public String reg(UserVo userVo) {
        logger.info("user is " + userVo.getUsername() + ", password is " + userVo.getPassword());
        registerService.reg(userVo);
        return "success";
    }

}
