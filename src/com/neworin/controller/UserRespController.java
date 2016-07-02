package com.neworin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.neworin.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给前端 返回json格式数据的控制器
 * Created by NewOrin Zhang on 2016/6/28.
 * Email: NewOrinZhang@Gmail.com
 */
@Controller
@RequestMapping("/userResp")
public class UserRespController {

    // 通过用户名获取用户信息
    // 不用restful getUserByUname?uname=yakov
    // 使用restful的风格 getUserByUname/{uname}
    @RequestMapping("/getUserByUname/{uname}")
    //@ResponseBody  // 可以放在方法上，也可以放在返回值之前
    public
    @ResponseBody
    User getUserByUname(@PathVariable String uname) {
        User user = new User(uname, "123");
        System.out.println("返回用户信息:" + user);
        return user;
    }
    @RequestMapping("/getUsers")
    @ResponseBody
    public Map<String, Object> getUsers() {
        // 从数据库中查找并封装数据集合对象 -- 模拟
        List<User> users = new ArrayList<User>();
        users.add(new User("y1", "111"));
        users.add(new User("y2", "222"));
        users.add(new User("y3", "333"));
        users.add(new User("y4", "444"));
        users.add(new User("y5", "555"));
        Map<String, Object> data = new HashMap<>();
        if (users.size() > 0) {
            data.put("data", users);
            data.put("success", true);
        } else {
            data.put("error", false);
            data.put("msg", "Can't find data!");
        }
        return data;
    }


    @RequestMapping("/getUsersJsonStr")
    @ResponseBody
    public String getUsersJsonStr() {

        // JSONObject/GSON/Fastjson
        // 从数据库中查找并封装数据集合对象 -- 模拟
        List<User> users = new ArrayList<User>();
        users.add(new User("y1", "111"));
        users.add(new User("y2", "222"));
        users.add(new User("y3", "333"));
        users.add(new User("y4", "444"));
        users.add(new User("y5", "555"));
        Map<String, Object> data = new HashMap<String, Object>();
        if(users.size() > 0) {
            data.put("data", users);
            data.put("success", true);
        } else {
            data.put("error", false);
            data.put("msg", "找不到数据！");
        }
        Gson gson = new Gson();
        JSON json = new JSONArray();

//		return "{'data':[{'username':'y1','userpwd':'111'},{'username':'y2','userpwd':'222'}],'success':true}";
//		return gson.toJson(data);
        return json.toJSONString(data);
    }



    @RequestMapping("/getUsersByResponseEntity")
    public ResponseEntity<Map<String, Object>> getUsersByResponseEntity() {

        // 从数据库中查找并封装数据集合对象 -- 模拟
        List<User> users = new ArrayList<User>();
        users.add(new User("y1", "111"));
        users.add(new User("y2", "222"));
        users.add(new User("y3", "333"));
        users.add(new User("y4", "444"));
        users.add(new User("y5", "555"));
        Map<String, Object> data = new HashMap<String, Object>();
        if(users.size() > 0) {
            data.put("data", users);
            data.put("success", true);
        } else {
            data.put("error", false);
            data.put("msg", "找不到数据！");
        }
        return new ResponseEntity(data, HttpStatus.OK);
    }


}
