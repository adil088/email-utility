package com.email_app.email_app.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v2/email-app")
public class FrontController {

    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("message", "Email utility");
        return "index";
    }

    @GetMapping("/applications")
    public String applications(Model model) {
        model.addAttribute("message", "View Applications");
        return "applications";
    }
}
