package com.example.OceanNews.Procedure;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Tasks {
    @Scheduled(fixedRate = 60000) // 60000 Run every 1 minute (60,000 milliseconds)
    public void myScheduledMethod() {
        // Your task logic goes here
        System.out.println("Running scheduled task...");
    }
}
