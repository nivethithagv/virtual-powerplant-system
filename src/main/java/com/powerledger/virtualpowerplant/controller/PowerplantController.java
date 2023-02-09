package com.powerledger.virtualpowerplant.controller;

import com.powerledger.virtualpowerplant.entity.Battery;
import com.powerledger.virtualpowerplant.service.BatteryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PowerplantController {

    @Autowired
    private BatteryService batteryService;

    private final Logger LOGGER =
            LoggerFactory.getLogger(PowerplantController.class);

    @PostMapping(value = "/batteries" )
    public String saveBatteries(@RequestBody List<Battery> batteries){

        LOGGER.info("Saving {[]} Batteries" , batteries.size());
        batteryService.saveBatteries(batteries);
        return "Welcome to Power plant300!!";
    }

}
