package com.foundit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }


   

    @GetMapping("/matches")
    public String matches() {
        return "matches";
    }

    @GetMapping("/handover")
    public String handover() {
        return "handover";
    }
}
