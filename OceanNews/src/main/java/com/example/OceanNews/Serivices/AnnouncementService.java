package com.example.OceanNews.Serivices;

import com.example.OceanNews.Model.Announcement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnnouncementService {

   Announcement add(Announcement announcement);
     String hardDelete(Long id);
     String softDelete(Long id);
     Announcement getById(Long id);
     Iterable<Announcement> getAll();
     Announcement update(Long id,Announcement announcement);
     List<Announcement> getAllByStatus(Long status);
     String restore(Long id);
     Announcement edit(Long id,Announcement announcement)throws Exception;
}
