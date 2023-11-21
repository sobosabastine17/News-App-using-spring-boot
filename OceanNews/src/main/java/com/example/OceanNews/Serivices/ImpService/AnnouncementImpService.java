package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Model.Announcement;
import com.example.OceanNews.Repo.RepoImplementation.RepoImp;
import com.example.OceanNews.Serivices.AnnouncementService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnnouncementImpService implements AnnouncementService {
    private final RepoImp<Announcement, Long> repo;
//    private final IJpaRepository<Announcement, Long> repo;
    public AnnouncementImpService(RepoImp<Announcement, Long> repo) {
        this.repo = repo;
    }
    @Override
    public Announcement addAnnouncement(Announcement announcement) {
           return repo.add(announcement);
    }

    @Override
    public void deleteAnnouncement(Long id) {
        repo.delete(id);
    }

    @Override
    public Optional<Announcement> getAnnouncementById(Long id) {
        return repo.GetById(id);
    }

    @Override
    public Iterable<Announcement> getAllAnnouncement() {
        return repo.getAll();
    }

    @Override
    public Announcement updateAnnouncement(Long id, Announcement announcement) {
        return repo.update(announcement,id);
    }
}
