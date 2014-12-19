package com.hdb.user.web.controller;

import com.haodaibao.user.entity.User;
import com.haodaibao.user.service.UserService;
import com.hdb.user.util.PasswordUtil;
import com.sun.javafx.sg.PGShape;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.security.util.ManifestEntryVerifier;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 2014/12/17.
 */
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String Index(HttpServletRequest httpServletRequest, Model model){
        logger.debug("no need auth");
        return "index";
    }

    @RequestMapping(value = "/login")
    public String showLoginForm(HttpServletRequest req, Model model) {
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "login";
    }

    @RequestMapping(value = "/auth")
    public String authInfo(HttpServletRequest httpServletRequest, Model model){
        return "index";
    }

    @RequestMapping(value = "register")
    public String register(HttpServletRequest httpServletRequest, Model model){
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String doReg(HttpServletRequest httpServletRequest, Model model){
        String userName = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        User user = new User();
        user.setUname(userName);
        user.setPwd(PasswordUtil.entryptPassword(password));

        return "redirect:/index";
    }
}
