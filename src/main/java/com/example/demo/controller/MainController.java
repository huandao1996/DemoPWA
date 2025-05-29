package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String ui1() {
        return "ui1";
    }

    @GetMapping("/ui2")
    public String ui2() {
        return "ui2";
    }

    @GetMapping("/ui3")
    public String ui3() {
        return "ui3";
    }
}