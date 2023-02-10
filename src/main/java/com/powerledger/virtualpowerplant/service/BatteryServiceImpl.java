package com.powerledger.virtualpowerplant.service;

import com.powerledger.virtualpowerplant.entity.Battery;
import com.powerledger.virtualpowerplant.repository.BatteryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation for the BatterService Interface.
 * Contains all the business logic
 *
 * @author nivi.vajravel
 */
@Service
public class BatteryServiceImpl implements BatteryService {

    @Autowired
    private BatteryRepository batteryRepository;

    private final Logger LOGGER =
            LoggerFactory.getLogger(BatteryServiceImpl.class);


    /**
     * Saves the list of batteries to H2 Database.
     * If a battery with same name exists within the postcode, then updates the battery watt capacity
     * If the battery doesn't exist in the postcode, then creates a new record
     *
     * @param batteries - List of battery objects
     * @return - list of updated battery objects
     */
    @Override
    public List<Battery> saveBatteries(List<Battery> batteries) {
        batteries.forEach(battery -> {

            //gets the list of batteries from db, with same name and postcode
            Battery exists = batteryRepository.findByNameAndCode(battery.getName(), battery.getPostcode());

            //if a battery with same name exists within the same postcode, then udpate the battery capacity
            if (Objects.nonNull(exists)) {
                LOGGER.info("Battery {} Already exists", battery.getName());
                LOGGER.info("Updating Battery Capacity of {} for postcode {}", battery.getName(), battery.getPostcode());
                battery.setBatteryId(exists.getBatteryId());
                battery.setWattCapacity(exists.getWattCapacity() + battery.getWattCapacity());
                batteryRepository.save(battery);
            } else {
                //if battery doesn't exist within the postcode, then create a new record
                batteryRepository.save(battery);
            }

        });
        return batteries;
    }

    /**
     * Gets the list of batteries in the given post code range.
     * Sorts the list alphabetically
     * Calculates the total and average watt capacity of the retrieved batteries in the
     * post code range
     *
     * @param from - postcode range from
     * @param to   - postcode range to
     * @return - returns a map of details
     */
    @Override
    public Map<String, Object> getBatteries(Integer from, Integer to) {

        List<Battery> batteries = batteryRepository.findByPostcodeBetween(from, to);

        //sorts alphabetically based on the battery name
        batteries.stream().sorted(Comparator.comparing(Battery::getName)).collect(Collectors.toList());

        //total watt capacity
        Double totalWattCapacity = batteries.stream().mapToDouble(battery -> battery.getWattCapacity()).sum();

        //average watt capacity
        Double avgWattCapacity = totalWattCapacity / batteries.size();

        HashMap<String, Object> map = new HashMap<>();
        map.put("batteries", batteries);
        map.put("totalWattCapacity", totalWattCapacity);
        map.put("avgWattCapacity", avgWattCapacity);

        return map;
    }
}
