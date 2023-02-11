package com.powerledger.virtualpowerplant.controller;

import com.powerledger.virtualpowerplant.entity.Battery;
import com.powerledger.virtualpowerplant.service.BatteryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PowerplantController.class)
public class PowerplantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BatteryService batteryService;

    private Battery battery;

    @BeforeEach
    void setUp() {
        battery = Battery.builder()
                .batteryId(1L)
                .name("testbattery")
                .postcode(6001)
                .wattCapacity(Double.valueOf(10.0))
                .build();
    }

    @Test
    void saveBatteries() throws Exception {
        Battery inputBattery = Battery.builder()
                .name("testbattery")
                .postcode(6001)
                .wattCapacity(Double.valueOf(10.0))
                .build();

        List<Battery> batteryList = new ArrayList<>();
        batteryList.add(inputBattery);

        List<Battery> outputBatteryList = new ArrayList<>();
        outputBatteryList.add(battery);

        Mockito.when(batteryService.saveBatteries(batteryList))
                .thenReturn(outputBatteryList);

        mockMvc.perform(post("/batteries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(" [\n { \n" +
                                "\t\"name\":\"testbattery\",\n" +
                                "\t\"postcode\":6001,\n" +
                                "\t\"wattCapacity\":10.0\n" +
                                "\n } \n] "))
                .andExpect(status().isOk());

    }

    @Test
    void getBatteries() throws Exception {

        List<Battery> outputBatteryList = Stream.of(
                Battery.builder().name("testbattery1").batteryId(1L).postcode(6001).wattCapacity(Double.valueOf(10.0)).build(),
                Battery.builder().name("testbattery2").batteryId(2L).postcode(6002).wattCapacity(Double.valueOf(10.0)).build()
        ).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("batteries", outputBatteryList);
        response.put("totalWattCapacity", 20.0);
        response.put("avgWattCapacity", 10.0);

        Mockito.when(batteryService.getBatteries(6001, 6002))
                .thenReturn(response);

        mockMvc.perform(get("/getbatteries?from=6001&to=6002"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalWattCapacity").value(20.0))
                .andExpect(jsonPath("$.avgWattCapacity").value(10.0))
                .andExpect(jsonPath("$.batteries").exists());

    }
}
