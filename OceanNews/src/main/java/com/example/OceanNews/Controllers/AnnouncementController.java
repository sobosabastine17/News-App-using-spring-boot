package com.example.OceanNews.Controllers;

import com.example.OceanNews.Model.Announcement;
import com.example.OceanNews.Serivices.ImpService.AnnouncementImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/newsApp/v1")
public class AnnouncementController {
    @Autowired
    AnnouncementImpService service;
    @PostMapping("/announcement/add")
    public ResponseEntity<String> add(@RequestBody Announcement announcement){
        if (service.add(announcement)!=null){
            return ResponseEntity.ok("Announcement added successfully");
        }
        return ResponseEntity.badRequest().body("Announcement could not be added");
    }
    @GetMapping("/announcement/getAll")
    public ResponseEntity<Iterable<Announcement>> getAllAnnouncement(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/announcement/getById/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        if (service.getById(id)!=null){
            return ResponseEntity.ok(service.getById(id));
        }
        return ResponseEntity.badRequest().body("Announcement with id "+id+" does not exist");
    }
  @PatchMapping("/announcement/update/{id}")
    public ResponseEntity update(@PathVariable Long id,@RequestBody Announcement announcement){
        if (service.getById(id)!=null){
            return ResponseEntity.ok(service.update(id,announcement));
        }
        return ResponseEntity.badRequest().body("Announcement with id "+id+" does not exist");
    }
    @DeleteMapping("/announcement/delete/hard/{id}")
    public ResponseEntity hardDelete(@PathVariable Long id){
        if (service.getById(id)!=null){
            return ResponseEntity.ok(service.hardDelete(id));
        }
        return ResponseEntity.badRequest().body("Announcement with id "+id+" does not exist");
    }
    @DeleteMapping("/announcement/delete/soft/{id}")
    public ResponseEntity softDelete(@PathVariable Long id){
        if (service.getById(id)!=null){
            return ResponseEntity.ok(service.softDelete(id));
        }
        return ResponseEntity.badRequest().body("Announcement with id "+id+" does not exist");
    }
    @GetMapping("/announcement/getAllByStatus/{status}")
    public ResponseEntity getAllByStatus(@PathVariable Long status){
//        if (service.findAnnouncementById(status).isEmpty()){
//            return ResponseEntity.badRequest().body("No announcement with status "+status+" found");
//        }
        return ResponseEntity.ok(service.getAllByStatus(status));
    }
    @PatchMapping("/announcement/restore/{id}")
    public ResponseEntity restore(@PathVariable Long id){
        if (service.getById(id)!=null){
            return ResponseEntity.ok(service.restore(id));
        }
        return ResponseEntity.badRequest().body("Announcement with id "+id+" does not exist");
    }
    // edit announcement
    @PatchMapping("/announcement/edit/{id}")
    public ResponseEntity edit(@PathVariable Long id,@RequestBody Announcement announcement) throws Exception {
        if (service.getById(id)!=null){
            return ResponseEntity.ok(service.edit(id,announcement));
        }
        return ResponseEntity.badRequest().body("Announcement with id "+id+" does not exist");
    }
}
