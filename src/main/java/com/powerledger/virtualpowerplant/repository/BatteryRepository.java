package com.powerledger.virtualpowerplant.repository;

import com.powerledger.virtualpowerplant.entity.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {

    public String storeBatteriesList(List<Battery> batteries);

    public List<Battery> findBatteriesByPostCodeRange(String fromCode, String toCode);


}
