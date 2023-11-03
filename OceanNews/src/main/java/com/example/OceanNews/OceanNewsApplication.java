package com.example.OceanNews;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.example.OceanNews")
@OpenAPIDefinition(info = @Info(title = "Ocean News", version = "1.0", description = "Ocean News API"))
public class OceanNewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OceanNewsApplication.class, args);

	}
  //write code that will print "Hello World" to the console

}
