package com.example.OceanNews.Modules.Announcement.Repository;

import com.example.OceanNews.Modules.Announcement.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
     @Query("select p from Announcement p where p.id = ?1")
     Announcement findByAnnouncementID(Long id);

     @Query("select p from Announcement p where p.status = ?1")
     List<Announcement> findAllByStatus(Long status);

//    @Query("select p from Post p where p.postID = ?1")
//    Post findByAnnouncementID(Long postID);
//
}
