package com.example.madera.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class HomeController {
    
    @Autowired
    private ResourceUrlProvider resourceUrlProvider;
    
    @GetMapping("/")
    public String home() {
        return "home";
    }

} 