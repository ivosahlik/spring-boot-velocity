package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-09-01
 */
@Controller
public class HomeController {

    @GetMapping("/home")
    public String test() {
        return "home";
    }

}
