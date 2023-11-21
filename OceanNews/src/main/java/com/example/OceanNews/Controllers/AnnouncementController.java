package com.example.OceanNews.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnouncementController {
    @PostMapping("/announcement/add")
    public String addAnnouncement(){
        return "announcement added";
    }
}
