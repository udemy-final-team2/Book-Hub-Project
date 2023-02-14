package com.example.BookHub;

import com.example.BookHub.User.UserDTO;
import com.example.BookHub.Util.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final HttpSession httpSession;

    @GetMapping("/")

    public String Home(Model model) {

        UserDTO userDTO = (UserDTO)  httpSession.getAttribute(SessionConst.LOGIN_USER);

        if (userDTO != null) {
            model.addAttribute("userName", userDTO.getName());
        }

        return "home";
    }

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