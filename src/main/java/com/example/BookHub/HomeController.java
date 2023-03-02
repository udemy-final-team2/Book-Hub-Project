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
    public String Home(HttpSession httpSession, Authentication authentication) {
        if (httpSession != null || (authentication != null && !authentication.getName().equals("anoymousUser"))) {
            return "redirect:/folder/list";
        }
        return "home";
    }

    @GetMapping("/signin")
    public String signIn(HttpSession httpSession, Authentication authentication) {
        if (httpSession != null || (authentication != null && !authentication.getName().equals("anoymousUser"))) {
            return "redirect:/folder/list";
        }
        return "signin";
    }

    @GetMapping("/signup")
    public String signUp(HttpSession httpSession, Authentication authentication) {
        if (httpSession != null || (authentication != null && !authentication.getName().equals("anoymousUser"))) {
            return "redirect:/folder/list";
        }
        return "signup";
    }

}