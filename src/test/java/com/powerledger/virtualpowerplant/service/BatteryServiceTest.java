package com.powerledger.virtualpowerplant.service;

import com.powerledger.virtualpowerplant.entity.Battery;
import com.powerledger.virtualpowerplant.repository.BatteryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BatteryServiceTest {

    @Autowired
    private BatteryService batteryService;

    @MockBean
    private BatteryRepository batteryRepository;

    @BeforeEach
    void setUp() {

        Battery battery1 = Battery.builder().name("b3").postcode(6003).wattCapacity(Double.valueOf(10.0)).build();
        Battery battery2 = Battery.builder().name("b2").postcode(6002).wattCapacity(Double.valueOf(20.0)).build();

        List<Battery> batteries = Stream.of(battery1, battery2).collect(Collectors.toList());
        Mockito.when(batteryRepository.findByPostcodeBetween(6001, 6002)).thenReturn(batteries);
    }

    @Test
    @DisplayName("Get Data based on Post Code Range")
    public void whenSaveListOfBatteries_thenReturnBatteriesList() {

        Battery battery1 = Battery.builder().batteryId(3L).name("b3").postcode(6003).wattCapacity(Double.valueOf(10.0)).build();
        Battery battery2 = Battery.builder().name("b2").postcode(6002).wattCapacity(Double.valueOf(20.0)).build();
        Mockito.when(batteryRepository.save(battery1)).thenReturn(battery1);
        Mockito.when(batteryRepository.findByNameAndCode("b2", 6002)).thenReturn(battery2);

        List<Battery> batteries = Stream.of(battery1, battery2).collect(Collectors.toList());

        List<Battery> outputBatteries = batteryService.saveBatteries(batteries);

        assertEquals(2, outputBatteries.size());
        //battery exists, hence capcity will be added
        assertEquals(40, outputBatteries.get(1).getWattCapacity());
    }

    @Test
    @DisplayName("Get Data based on Post Code Range")
    public void whenValidpostCodeRange_thenReturnResponseMap() {
        Map<String, Object> response = batteryService.getBatteries(6001, 6002);

        assertEquals(15.0, response.get("avgWattCapacity"));
        assertEquals(30.0, response.get("totalWattCapacity"));
    }
}
