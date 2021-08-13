package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class BaseController {
    @GetMapping("/")
    public String index(){
        return  "<a href=\"http://localhost:8080/poll/main/polls\">Login</a>";
    }
}
