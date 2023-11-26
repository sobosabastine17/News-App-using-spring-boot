package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Repo.SettingsRepo;
import com.example.OceanNews.Serivices.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsImpService implements SettingsService {
    @Autowired
    SettingsRepo repo;
    @Override
    public void setApproveComment(boolean approveComment) {
          repo.findById((long) 1).get().setApproveComment(approveComment);
    }
}
