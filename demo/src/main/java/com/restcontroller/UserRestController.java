package com.restcontroller;

import com.service.User1Service;
import com.service.UserService;
import com.service.UserTestService;
import com.vo.Result;
import com.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTestService userTestService;

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @RequestMapping("/list")
    public List<UserVo> list(UserVo userVo) {
        return userService.list(userVo);
    }

    @RequestMapping("/listData")
    public Object listData(UserVo userVo) {
        Map map = new HashMap<String, Object>();
        map.put("data", userService.list(userVo));
        return map;
    }

    @RequestMapping("/add")
    public Result add(UserVo userVo) {
        return userService.add(userVo);
    }

    @RequestMapping("/test")
    public String test() {
        userTestService.test();
        return "suc";
    }
}
