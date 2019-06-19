package com.controller.web;

import com.entity.Resource;
import com.service.ResourceService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/")
    public String index(Model model) {
        String username = getUsername();
        Set<String> permissions = userService.findPermissions(username);
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        model.addAttribute("username", username);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
