package com.controller.web;

import com.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(HttpServletRequest request, Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        String username = user.getUsername();
        LOGGER.info("准备登录用户：{}", username);

        UsernamePasswordToken token = new UsernamePasswordToken(username, user.getPassword());
        // 获取当前的subject
        Subject currentUser = SecurityUtils.getSubject();

        try {
            /**
             * 在调用了login方法后，SecurityManager会收到AuthenticationToken，并将其发送给配置的realm执行必须的认证检查
             * 每个realm都能在必要时对提交的AuthenticationToken做出反应
             * 所以这一步在调用login时，会走到Realm的doGetAuthenticationInfo方法中
             */
            LOGGER.info("对用户{}进行登录验证，验证开始", username);
            currentUser.login(token);
            LOGGER.info("对用户{}进行登录验证，验证通过", username);
        } catch (UnknownAccountException e) {
            LOGGER.info("对用户{}进行登录验证，验证未通过，未知的用户", username);
            redirectAttributes.addFlashAttribute("message", "未知账户");
        } catch (IncorrectCredentialsException e) {
            LOGGER.info("对用户{}进行登录验证，验证未通过，错误的凭证", username);
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        } catch (LockedAccountException e) {
            LOGGER.info("对用户{}进行登录验证，验证未通过，账户已锁定", username);
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        } catch (ExcessiveAttemptsException e) {
            LOGGER.info("对用户{}进行登录验证，验证未通过，错误次数过多", username);
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        } catch (AuthenticationException e) {
            LOGGER.info("对用户{}进行登录验证，验证未通过，堆栈轨迹如下", username);
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }

        // 验证登录是否成功
        if (currentUser.isAuthenticated()) {
            LOGGER.info("用户{}登录认证通过", username);
            return "redirect:/";
        } else {
            token.clear();
            return "redirect:/login";
        }
    }

    @RequestMapping("logout")
    public String logout(RedirectAttributes redirectAttributes) {
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:/login";
    }

}
