package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/tabPage")
    public String tabPage(String page){
        if(page==null &&  "".equals(page)){
            return "welcome";
        }
        return ("page/"+page);
    }

}
