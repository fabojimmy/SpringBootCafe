package com.inn.cafe.cafe.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class autre {

    @GetMapping("/autre")
    public String getrString() {


        return "bonjour le monde";
    }
    
}
