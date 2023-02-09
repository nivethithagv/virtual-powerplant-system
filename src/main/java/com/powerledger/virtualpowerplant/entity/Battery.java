package com.powerledger.virtualpowerplant.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Battery {

    private Long batteryId;
    private String name;
    private Integer postcode;
    transient private Float wattCapacity;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getBatteryId() {
        return batteryId;
    }

    public void setBatteryId(Long batteryId) {
        this.batteryId = batteryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public Float getWattCapacity() {
        return wattCapacity;
    }

    public void setWattCapacity(Float wattCapacity) {
        this.wattCapacity = wattCapacity;
    }

    public Battery(String name, Integer postcode, Float wattCapacity) {
        this.name = name;
        this.postcode = postcode;
        this.wattCapacity = wattCapacity;
    }

    public Battery() {
    }

    @Override
    public String toString() {
        return "Battery{" +
                "batteryId=" + batteryId +
                ", name='" + name + '\'' +
                ", postcode=" + postcode +
                ", wattCapacity=" + wattCapacity +
                '}';
    }
}
