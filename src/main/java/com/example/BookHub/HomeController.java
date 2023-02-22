package com.example.BookHub;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class HomeController {
    @GetMapping("/")
    public String Home() {return "home";}

    @GetMapping("/signin")
    public String signIn() {return "signin";}

    @GetMapping("/signup")
    public String signUp() {return "signup";}

    @PostMapping("/save")
    @ResponseBody
    public String saveEditorContent(@RequestParam("editorContent") String editorContent,
                                    @RequestParam("title") String title, @RequestParam("memo") String memo) {

        return "success";
    }
}