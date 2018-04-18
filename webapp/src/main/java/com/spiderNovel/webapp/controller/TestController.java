package com.spiderNovel.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("test")
    public String gotestpage(){
        return "test";
    }
}
