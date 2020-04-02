package com.learning.cmsdemo.controller;

import com.learning.cmsdemo.model.ConferenceRecord;
import com.learning.cmsdemo.model.User;
import com.learning.cmsdemo.service.ConferenceService;
import com.learning.cmsdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    ConferenceService conferenceService;

    @RequestMapping("/admin/user")
    public String showUser(Model model){
        List<User> users=userService.selectAll();
        model.addAttribute("users",users);

        return "userManage";

    }

    @RequestMapping("/admin/conference")
    public String showConference(Model model){
        List<ConferenceRecord> records=conferenceService.showAll();
        model.addAttribute("records",records);

        return "conferenceManage";

    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }

    @GetMapping("/deleteConference/{conferenceID}")
    public String deleteCon(@PathVariable String conferenceID){
        conferenceService.deleteConferenceID(conferenceID);
        return "redirect:/admin/conference";
    }

}
