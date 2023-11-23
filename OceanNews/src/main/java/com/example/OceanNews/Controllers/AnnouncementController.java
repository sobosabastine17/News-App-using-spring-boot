package com.example.OceanNews.Controllers;

import com.example.OceanNews.Model.Announcement;
import com.example.OceanNews.Serivices.ImpService.AnnouncementImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnouncementController {
    @Autowired
    AnnouncementImpService service;
    @PostMapping("/announcement/add")
    public ResponseEntity<String> addAnnouncement(@RequestBody Announcement announcement){
        if (service.addAnnouncement(announcement)!=null){
            return ResponseEntity.ok("Announcement added successfully");
        }
        return ResponseEntity.badRequest().body("Announcement could not be added");
    }
    @GetMapping("/announcement/getAll")
    public ResponseEntity<Iterable<Announcement>> getAllAnnouncement(){
        return ResponseEntity.ok(service.getAllAnnouncement());
    }
}
