package com.powerledger.virtualpowerplant.service;

import com.powerledger.virtualpowerplant.entity.Battery;

import java.util.List;
import java.util.Map;

/**
 * Service Class for Batteries API
 *
 * @author nivi.vajravel
 */
public interface BatteryService {
    List<Battery> saveBatteries(List<Battery> batteries);

    Map<String, Object> getBatteries(Integer from, Integer to);
}
