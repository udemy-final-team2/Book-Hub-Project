package com.example.BookHub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String Home() {
        return "home";
    }

    @GetMapping("/signin")
    public String Login() {return "signin";}

    @GetMapping("/signup")
    public String Register() {return "signup";}
}