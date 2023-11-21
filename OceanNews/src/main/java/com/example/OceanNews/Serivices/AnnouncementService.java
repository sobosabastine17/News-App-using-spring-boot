package com.example.OceanNews.Serivices;

import com.example.OceanNews.Model.Announcement;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AnnouncementService {
     Announcement addAnnouncement(Announcement announcement);
     void deleteAnnouncement(Long id);
     Optional<Announcement> getAnnouncementById(Long id);
     Iterable<Announcement> getAllAnnouncement();
     Announcement updateAnnouncement(Long id,Announcement announcement);
}
