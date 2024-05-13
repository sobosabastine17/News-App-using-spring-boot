package com.example.OceanNews.Modules.Settings.ServiceImplementation;

import com.example.OceanNews.Modules.Settings.Repository.SettingsRepository;
import com.example.OceanNews.Modules.Settings.Service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsImpService implements SettingsService {
    @Autowired
    SettingsRepository repo;
    @Override
    public void setApproveComment(boolean approveComment) {
          repo.findById((long) 1).get().setApproveComment(approveComment);
    }
}
