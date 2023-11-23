package com.example.OceanNews.Repo;

import com.example.OceanNews.Model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepo extends JpaRepository<Announcement, Long> {

}
