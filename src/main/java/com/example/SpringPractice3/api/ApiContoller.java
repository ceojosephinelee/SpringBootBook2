package com.example.SpringPractice3.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiContoller {
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World";
    }

}
