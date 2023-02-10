package com.powerledger.virtualpowerplant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Entity class for Battery
 *
 * @author nivi.vajravel
 */
@Entity
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long batteryId;
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(value = 6000, message = "Invalid Postcode. WA Postcode range is 6000 - 6997")
    @Max(value = 6997, message = "Invalid Postcode. WA Postcode range is 6000 - 6997")
    @NotNull(message = "Postcode is mandatory")
    private Integer postcode;
    private Float wattCapacity;


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
        return "Battery{" + "batteryId=" + batteryId
                + ", name='" + name + '\''
                + ", postcode=" + postcode
                + ", wattCapacity=" + wattCapacity
                + '}';
    }
}
