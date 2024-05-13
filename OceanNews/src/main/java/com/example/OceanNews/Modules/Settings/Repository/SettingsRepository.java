package com.example.OceanNews.Modules.Settings.Repository;

import com.example.OceanNews.Modules.Settings.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {
}
