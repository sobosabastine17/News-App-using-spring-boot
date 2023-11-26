package com.example.OceanNews.Repo;

import com.example.OceanNews.Model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepo extends JpaRepository<Settings, Long> {
}
