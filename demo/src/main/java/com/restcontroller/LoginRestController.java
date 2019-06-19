package com.restcontroller;

import com.annotation.Trim;
import com.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/api")
public class LoginRestController {

    private static final Logger logger = LoggerFactory.getLogger(LoginRestController.class);

    @RequestMapping(value = "/login")
    @Trim
    public String login(UserVo userVo) {
        logger.info("user is " + userVo.getUsername() + ", password is " + userVo.getPassword());
        return "LoginRest";
    }

    @RequestMapping(value = "/verifyCode")
    public String verifyCode(HttpSession httpSession) {
        String sessionId = httpSession.getId();
        httpSession.setAttribute("session_id", sessionId);
        return sessionId;
    }

    @RequestMapping(value = "/checkCode")
    public String checkCode(String sessionId, HttpSession httpSession) {
        String sessionIdCheck = (String) httpSession.getAttribute("session_id");
        String result = "session: " + sessionId + ", sessionIdCheck: " + sessionIdCheck;
        if (sessionIdCheck != null) {
            result = result + ", equals: " + sessionIdCheck.equalsIgnoreCase(sessionId);
        }
        return result;
    }
}
