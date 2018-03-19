package com.tone.shiro.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.tone.shiro.JWTUtil;
import com.tone.shiro.UnauthorizedException;
import com.tone.shiro.entity.ResponseBean;
import com.tone.shiro.entity.UserBean;
import com.tone.shiro.service.UserService;

/**
 * @author zhaoxiang.liu
 * @date 2018/2/8
 */
@RestController
public class HomeController {
    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public ResponseBean login(@RequestParam("username") String username, @RequestParam("password") String password,
            HttpServletRequest request, HttpServletResponse response) {
        UserBean userBean = service.getUser(username);
        if(userBean.getPassword().equals(password)) {
            String jwt = JWTUtil.sign(username, password);
            Cookie cookie = new Cookie("Authorization", jwt);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            // response.addHeader("Set-Cookie", "Authorization=" + jwt + "; Path=/; HttpOnly");
            return new ResponseBean(200, "Login success", jwt);
        }else {
            throw new UnauthorizedException();
        }
    }

    @GetMapping("/article")
    public ResponseBean article() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            return new ResponseBean(200, "You are already logged in", null);
        }else {
            return new ResponseBean(200, "You are guest", null);
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseBean requireAuth() {
        return new ResponseBean(200, "You are authenticated", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResponseBean requireRole() {
        return new ResponseBean(200, "You are visiting require_role", null);
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = { "view", "edit" })
    public ResponseBean requirePermission() {
        return new ResponseBean(200, "You are visiting permission require edit,view", null);
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized() {
        return new ResponseBean(401, "Unauthorized", null);
    }
}
