package com.example.SpringPractice3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model) { //모델 객체 받아오기
        model.addAttribute("username", "이지현");
        return "greetings";
    }
    @GetMapping("/bye")
    public String byeToMeetYou(Model model) {
        model.addAttribute("username", "이지현");
        return "goodbye";
    }
}
