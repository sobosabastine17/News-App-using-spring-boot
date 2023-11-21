package com.example.OceanNews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.OceanNews")
public class OceanNewsApplication {
	public static void main(String[] args) {
		SpringApplication.run(OceanNewsApplication.class, args);

	}
  //write code that will print "Hello World" to the console

}
