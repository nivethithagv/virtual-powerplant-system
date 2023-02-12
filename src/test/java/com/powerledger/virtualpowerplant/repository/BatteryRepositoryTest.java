package com.powerledger.virtualpowerplant.repository;

import com.powerledger.virtualpowerplant.entity.Battery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BatteryRepositoryTest {

    @Autowired
    private BatteryRepository batteryRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Battery battery = Battery.builder()
                .name("testbattery")
                .postcode(6001)
                .wattCapacity(Double.valueOf(10.0))
                .build();

        Battery battery2 = Battery.builder()
                .name("testbattery2")
                .postcode(6002)
                .wattCapacity(Double.valueOf(15.0))
                .build();

        entityManager.persist(battery);
        entityManager.persist(battery2);
    }

    @Test
    public void whenSaveBattery_thenReturnBattery() {
        Battery inputBattery = Battery.builder()
                .name("testbattery2")
                .postcode(6001)
                .wattCapacity(Double.valueOf(10.0))
                .build();

        Battery outputBattery = batteryRepository.save(inputBattery);
        assertEquals(outputBattery.getName(), "testbattery2");
    }

    @Test
    public void whenFindByNameAndCode_thenReturnBattery() {
        Battery outputBattery = batteryRepository.findByNameAndCode("testbattery", 6001);
        assertEquals(outputBattery.getName(), "testbattery");
        assertEquals(outputBattery.getPostcode(), 6001);
        assertEquals(outputBattery.getWattCapacity(), 10);
    }

    @Test
    public void whenFindByPostcodeBetween_thenReturnBatteries() {
        Battery battery3 = Battery.builder()
                .name("testbattery3")
                .postcode(6001)
                .wattCapacity(Double.valueOf(20.0))
                .build();

        entityManager.persist(battery3);

        List<Battery> outputBatteries = batteryRepository.findByPostcodeBetween(6001, 6002);
        assertEquals(outputBatteries.size(), 3);
    }

}
