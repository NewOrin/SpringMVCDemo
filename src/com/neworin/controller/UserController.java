package com.neworin.controller;

import com.neworin.entity.User;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 用户控制，获取json格式的对象参数
 * Created by NewOrin Zhang on 2016/6/28.
 * Email: NewOrinZhang@Gmail.com
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = "/login_requestbody", method = RequestMethod.POST)
    public void login1(@RequestBody User user) {
        System.out.println("使用@RequestBody获取到的参数对象为:" + user);
//        return "result";
    }

    @RequestMapping(value = "/login_httpEntity", method = RequestMethod.POST)
    public void login2(HttpEntity<User> entity) {
        System.out.println("使用HttpEntity获取到的请求参数为:" + entity);
        System.out.println("使用HttpEntity获取到的参数对象为:" + entity.getBody());
    }

    @RequestMapping("login_data")
    public String login3(User user, ModelMap modelMap) {
        System.out.println("使用@RequestBody获取到的参数对象为：" + user);
        modelMap.put("username1", user.getUsername());
        modelMap.addAttribute("username2", user.getUsername());
        return "result";
    }

}
