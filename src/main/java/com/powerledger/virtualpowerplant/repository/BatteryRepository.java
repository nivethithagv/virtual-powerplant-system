package com.powerledger.virtualpowerplant.repository;

import com.powerledger.virtualpowerplant.entity.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to Connect to H2 Database
 * Extends JPARepository to take advantage of available methods
 *
 * @author nivi.vajravel
 */
@Repository
public interface BatteryRepository extends JpaRepository<Battery, String> {

    public Battery save(Battery battery);

    @Query("SELECT b from Battery b WHERE b.name=?1 AND b.postcode=?2")
    public Battery findByNameAndCode(String name, Integer postcode);

    public List<Battery> findByPostcodeBetween(Integer from, Integer to);
}
