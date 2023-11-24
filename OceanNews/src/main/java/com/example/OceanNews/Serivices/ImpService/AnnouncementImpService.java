package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Model.Announcement;
import com.example.OceanNews.Repo.AnnouncementRepo;
import com.example.OceanNews.Serivices.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementImpService implements AnnouncementService {
   @Autowired
   AnnouncementRepo repo;
    @Override
    public Announcement add(Announcement announcement) {
           return repo.save(announcement);
    }

    @Override
    public String hardDelete(Long id) {
        if (repo.findById(id).isEmpty()){
            return "Announcement with id "+id+" does not exist";
        }
        repo.deleteById(id);
        return "Announcement with id "+id+" has been deleted";
    }
    @Override
    public String softDelete(Long id) {
        if (repo.findById(id).isEmpty()){
            return "Announcement with id: "+id+" does not exist";
        }
        Announcement announcement = repo.findById(id).get();
        announcement.setStatus(0L);
        repo.save(announcement);
        return "Announcement with id "+id+" has been deleted";
    }

    @Override
    public Announcement getById(Long id) {
        if (repo.findById(id).isEmpty()){
            return null;
        }
       return repo.findById(id).get();
    }

    @Override
    public Iterable<Announcement> getAll() {
        return repo.findAll();
    }

    @Override
    public Announcement update(Long id, Announcement announcement) {
       // Announcement existing = repo.findByAnnouncementID(id);
        Announcement existing = repo.findById(id).orElse(null);
        // assert existing != null;
        if (existing == null){
            return null;
        }
        existing.setTitle(announcement.getTitle());
        existing.setContent(announcement.getContent());
        existing.setImage(announcement.getImage());
        existing.setRecipient(announcement.getRecipient());
        existing.setCreatedBy(announcement.getCreatedBy());
        return repo.save(existing);
    }

    @Override
    public List<Announcement> getAllByStatus(Long status) {
        repo.findByAnnouncementID(status);
        return repo.findAllByStatus(status);
    }

    @Override
    public String restore(Long id) {
        if (repo.findById(id).isEmpty()){
            return "Announcement with id: "+id+" does not exist";
        }
        Announcement announcement = repo.findById(id).get();
        announcement.setStatus(1L);
        repo.save(announcement);
        return "Announcement with id "+id+" has been Restored";
    }

    @Override
    public ResponseEntity<String> findAnnouncementById(Long id) {
        if (!repo.existsById(id)){
             return ResponseEntity.badRequest().body("Announcement with id "+id+" does not exist");
        }
        // return ResponseEntity.ok("Announcement with id "+id+" exists");
        return ResponseEntity.badRequest().body("Announcement with id "+id+" does not exist");
    }
}
