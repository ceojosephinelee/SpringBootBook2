package com.example.SpringPractice3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {
    @GetMapping("/random-quote")
    public String randomQuote(Model model) {
        String[] quotes ={
                "행복은 습관이다. 그것을 몸에 지녀라"," 고개 숙이지 마십시오","당신이 할 수 잇다고 믿는 아니든 그대로 될 것이다"
        };
        int randInt = (int) (Math.random() * quotes.length);
        model.addAttribute("quote",quotes[randInt]);
        return "random-quote";
    }
}
