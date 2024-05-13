package com.example.OceanNews.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify/newsApp/callback")
public class CallBackController {
    @PostMapping("/user/email")
    public String emailVerification() {
        return "This is a callback function";
    }
}
