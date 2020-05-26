package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//@Controller
@RestController
@RequestMapping("/welcome")
public class WelcomeController {
    public static Log log = LogFactory.getLog(WelcomeController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("getUser/{id}")
    public String getUser(@PathVariable Integer id){
        User user = userService.selectByPrimaryKey(id);
//        User user = userService.sel(id);
        return user.toString();
    }

    @RequestMapping("home")
    public String home(){
//        log.info("-----------> log.info");
//        log.debug("-----------> log.debug");
//        log.error("-----------> log.error");
        return "welcome";
    }

    @RequestMapping("/welcomeIndex")
    public ModelAndView welcomeIndex(){
//        System.out.println("------------------>welcomeIndex");

        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome");
        mv.addObject("name","zhuheng");
        return mv;
    }
}
