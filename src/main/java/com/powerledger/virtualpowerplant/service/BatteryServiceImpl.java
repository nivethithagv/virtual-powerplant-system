package com.powerledger.virtualpowerplant.service;

import com.powerledger.virtualpowerplant.entity.Battery;
import com.powerledger.virtualpowerplant.repository.BatteryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BatteryServiceImpl implements BatteryService {

    @Autowired
    private BatteryRepository batteryRepository;

    private final Logger LOGGER =
            LoggerFactory.getLogger(BatteryServiceImpl.class);


    @Override
    public List<Battery> saveBatteries(List<Battery> batteries) {
        batteries.forEach(battery -> {

            Battery exists = batteryRepository.findByNameAndCode(battery.getName(), battery.getPostcode());

            if (Objects.nonNull(exists)) {
                LOGGER.info("Battery {} Already exists", battery.getName());
                LOGGER.info("Updating Battery Capacity of {} for postcode {}", battery.getName(), battery.getPostcode());
                battery.setBatteryId(exists.getBatteryId());
                battery.setWattCapacity(exists.getWattCapacity() + battery.getWattCapacity());
                batteryRepository.save(battery);
            } else {
                batteryRepository.save(battery);
            }

        });
        return batteries;
    }

    @Override
    public Map<String, Object> getBatteries(Integer from, Integer to) {

        List<Battery> batteries = batteryRepository.findByPostcodeBetween(from, to);
        batteries.stream().sorted(Comparator.comparing(Battery::getName)).collect(Collectors.toList());


        Double totalWattCapacity = batteries.stream().mapToDouble(battery -> battery.getWattCapacity()).sum();
        Double avgWattCapacity = totalWattCapacity / batteries.size();

        HashMap<String, Object> map = new HashMap<>();
        map.put("batteries", batteries);
        map.put("totalWattCapacity", totalWattCapacity);
        map.put("avgWattCapacity", avgWattCapacity);

        return map;
    }
}
