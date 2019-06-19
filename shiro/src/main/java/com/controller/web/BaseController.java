package com.controller.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

@Component
public class BaseController {
    protected String getUsername() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return (String) currentUser.getPrincipal();
        }
        return null;
    }
}
