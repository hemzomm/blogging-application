package com.hem101.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RandController {

    @PostMapping("/rand")
    ResponseEntity<String > post(){
return new ResponseEntity<>("Hello from APi", HttpStatus.OK);
    }
}
