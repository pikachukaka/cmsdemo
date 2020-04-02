package com.learning.cmsdemo.controller;

import com.learning.cmsdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/user/login")
    public String insertUser(@RequestParam("username")String id, @RequestParam("password")String password, Map<String,Object> map){
        if(userService.insertUser(id,password)){
            map.put("registerMSG","注册成功！");
            return "login";
        }
        else {
            map.put("registerMSG","用户名已存在");
            return "register";
        }
    }

    @PostMapping("/user/main")
    public String login(@RequestParam("username")String id, @RequestParam("password")String password,
                        Map<String,Object> map, HttpSession session){
        if(id.equals("admin")&&password.equals("000000")) {
            session.setAttribute("loginUser",id);
            return "redirect:/admin.html";
        }
        else{
            int result=userService.login(id,password);
            if(result==1) {
                session.setAttribute("loginUser",id);
                return "redirect:/main.html";
            }
            else if(result==0) {
                map.put("loginMSG","用户不存在");
            }
            else {
                map.put("loginMSG","密码错误");
            }
            return "login";
        }

    }

}
