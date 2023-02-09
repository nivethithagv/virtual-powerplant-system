package com.powerledger.virtualpowerplant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PowerplantController {

    @GetMapping(value = "/" )
    public String Helloworld(){
        return "Welcome to Power plant300!!";
    }

}
