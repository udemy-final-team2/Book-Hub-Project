package com.example.BookHub.User;

import com.example.BookHub.Util.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final HttpSession httpSession;


    @GetMapping("/signin")
    public String Login() {

        return "signin";
    }


}
