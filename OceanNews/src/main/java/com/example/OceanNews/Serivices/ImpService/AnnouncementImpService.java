package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Model.Announcement;
import com.example.OceanNews.Repo.AnnouncementRepo;
import com.example.OceanNews.Serivices.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnnouncementImpService implements AnnouncementService {
   @Autowired
   AnnouncementRepo repo;
    @Override
    public Announcement addAnnouncement(Announcement announcement) {
           return repo.save(announcement);
    }

    @Override
    public void deleteAnnouncement(Long id) {
        repo.delete(getAnnouncementById(id).get());
    }

    @Override
    public Optional<Announcement> getAnnouncementById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Iterable<Announcement> getAllAnnouncement() {
        return repo.findAll();
    }

    @Override
    public Announcement updateAnnouncement(Long id, Announcement announcement) {
        return repo.save(announcement);
    }
}
