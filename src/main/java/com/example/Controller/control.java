package com.example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
  


@RestController
public class control {
    @GetMapping("/")
    public String index() {
        return "Hola";
    }
    
}
