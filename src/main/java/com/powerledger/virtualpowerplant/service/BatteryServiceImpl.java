package com.powerledger.virtualpowerplant.service;

import com.powerledger.virtualpowerplant.entity.Battery;
import com.powerledger.virtualpowerplant.repository.BatteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatteryServiceImpl implements BatteryService {

    @Autowired
    private BatteryRepository batteryRepository;

    @Override
    public List<Battery> saveBatteries(List<Battery> batteries) {
        return batteryRepository.saveAll(batteries);

    }
}
