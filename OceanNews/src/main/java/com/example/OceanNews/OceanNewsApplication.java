package com.example.OceanNews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.OceanNews")
@EnableScheduling
@EnableWebMvc
public class OceanNewsApplication {
	public static void main(String[] args) {
		SpringApplication.run(OceanNewsApplication.class, args);
	}
}