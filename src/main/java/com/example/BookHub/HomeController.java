package com.example.BookHub;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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