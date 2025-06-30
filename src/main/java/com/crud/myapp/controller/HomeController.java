package com.crud.myapp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage(HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        return "home";
    }
}
