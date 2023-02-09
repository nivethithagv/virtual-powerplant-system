package com.powerledger.virtualpowerplant.service;

import com.powerledger.virtualpowerplant.entity.Battery;

import java.util.List;

public interface BatteryService {
    List<Battery> saveBatteries(List<Battery> batteries);
}
