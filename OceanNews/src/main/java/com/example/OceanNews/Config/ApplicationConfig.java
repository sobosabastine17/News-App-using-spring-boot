package com.example.OceanNews.Config;

import com.example.OceanNews.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepo userRepo;
    @Bean
    public UserDetailsService userDetailsService()
    {
        return userRepo::findByEmail;
        //return username -> userRepo.findByEmail(username);
    }
}
