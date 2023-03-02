package com.example.BookHub;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class HomeController {

    @GetMapping("/")
    public String Home() {return "home";}

    @GetMapping("/signin")
    public String signIn() {return "signin";}

    @GetMapping("/signup")
    public String signUp() {return "signup";}

}