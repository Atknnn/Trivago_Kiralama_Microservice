package com.atakan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @GetMapping("/auth")
    public ResponseEntity<String> getFallbackAuth(){
        return ResponseEntity.ok("Auth microservisi şuan hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz.");
    }
    @GetMapping("/product")
    public ResponseEntity<String> getFallbackProduct(){
        return ResponseEntity.ok("Product microservisi şuan hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz.");
    }
    @GetMapping("/user")
    public ResponseEntity<String> getFallbackUser(){
        return ResponseEntity.ok("UserProfile microservisi şuan hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz.");
    }

}
