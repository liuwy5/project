package com.controller;

import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    public String list(Model model) {
//        model.addAttribute("userList", userService.list(null));
        return "userList";
    }

    @RequestMapping("/user/list/table")
    public String listTable(Model model) {
        model.addAttribute("userList", userService.list(null));
        return "userListTable";
    }
}
