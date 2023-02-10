package com.powerledger.virtualpowerplant.repository;

import com.powerledger.virtualpowerplant.entity.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, String> {

    public Battery save(Battery battery);

  @Query("SELECT b from Battery b WHERE b.name=?1 and b.postcode=?2")
    public Battery findByNameAndCode(String name, Integer postcode);
}
