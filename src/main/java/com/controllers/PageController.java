package com.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {

    @GetMapping
    @RequestMapping(value="/")
    public String mainPage(){
        return "index";
    }

    @GetMapping
    @RequestMapping(value="/json")
    public ResponseEntity getJson(){
        return new ResponseEntity<>("test",HttpStatus.ACCEPTED);
    }
}
