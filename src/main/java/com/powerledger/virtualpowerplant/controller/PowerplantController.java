package com.powerledger.virtualpowerplant.controller;

import com.powerledger.virtualpowerplant.entity.Battery;
import com.powerledger.virtualpowerplant.service.BatteryService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class PowerplantController {

    @Autowired
    private BatteryService batteryService;

    private final Logger LOGGER =
            LoggerFactory.getLogger(PowerplantController.class);

    @PostMapping(value = "/batteries")
    public @ResponseBody List<Battery> saveBatteries(@RequestBody List<Battery> batteries) {
        LOGGER.info("Saving {} Batteries", batteries.size());
        return batteryService.saveBatteries(batteries);
    }

    @GetMapping(value = "/getbatteries")
    public @ResponseBody Map<String, Object> getBatteries(@RequestParam @Min(6000) @Max(6997) Integer from,
                                                          @RequestParam @Min(6000) @Max(6997) Integer to) {
        LOGGER.info("Fetching Batteries from postcode {} to {}", from, to);
        Map<String, Object> response = batteryService.getBatteries(from, to);
        return response;
    }

}
