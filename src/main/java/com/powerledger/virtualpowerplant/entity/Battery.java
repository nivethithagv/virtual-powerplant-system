package com.powerledger.virtualpowerplant.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for Battery
 *
 * @author nivi.vajravel
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long batteryId;
    @NotBlank(message = "Name is mandatory")
    @Size(min=1, max = 100, message = "Name length should be >0 and <100")
    private String name;

    @Min(value = 6000, message = "Invalid Postcode. WA Postcode range is 6000 - 6997")
    @Max(value = 6997, message = "Invalid Postcode. WA Postcode range is 6000 - 6997")
    @NotNull(message = "Postcode is mandatory")
    private Integer postcode;

    @Positive(message = "Watt Capacity cannot be Negative")
    private Double wattCapacity;


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

    public Double getWattCapacity() {
        return wattCapacity;
    }

    public void setWattCapacity(Double wattCapacity) {
        this.wattCapacity = wattCapacity;
    }

    public Battery(String name, Integer postcode, Double wattCapacity) {
        this.name = name;
        this.postcode = postcode;
        this.wattCapacity = wattCapacity;
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
