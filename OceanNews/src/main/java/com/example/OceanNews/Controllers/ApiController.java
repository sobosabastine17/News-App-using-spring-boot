package com.example.OceanNews.Controllers;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
@RestController
@ApiOperation(value = "Ocean News", tags = {"Ocean News"})
public class ApiController {
    @Value("${app.name}")
    private String appName;
    @Value("${app.version}")
    private String appVersion;
    @GetMapping("version")
    public String appDetails(){
        String details= "App Name"+"-"+appName+"\n App Version"+"-"+appVersion;
        return details;
    }
//Testing the api
    @GetMapping("app/testing")
    public String testingApp(){
        return "Welcome to me";
    }
}
