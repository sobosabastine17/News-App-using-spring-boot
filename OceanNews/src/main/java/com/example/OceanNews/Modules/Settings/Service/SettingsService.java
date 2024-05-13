package com.example.OceanNews.Modules.Settings.Service;

import org.springframework.stereotype.Service;

@Service
public interface SettingsService {
    void setApproveComment(boolean approveComment);
}
