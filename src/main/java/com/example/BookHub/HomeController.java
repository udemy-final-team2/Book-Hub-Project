package com.example.BookHub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/signin")
    public String signIn() {return "signin";}

    @GetMapping("/signup")
    public String signUp() {return "signup";}

    @GetMapping("/guest/qna")
    public String QnA() {
        return "/qna";
    }

    @GetMapping("/docs")
    public String myPage() {
        return "docs";
    }
}