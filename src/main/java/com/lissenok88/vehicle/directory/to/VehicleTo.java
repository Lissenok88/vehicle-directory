package com.lissenok88.vehicle.directory.to;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class VehicleTo extends BaseTo{

    @NotBlank
    String stateNumber;

    @NotBlank
    String make;

    @NotBlank
    String model;

    @NotBlank
    String category;

    @NotBlank
    String type;

    @NotBlank
    String year;

    @NotBlank
    String trailer;

    public VehicleTo(Long id, String stateNumber, String make, String model, String category, String type, String year, String trailer) {
        super(id);
        this.stateNumber = stateNumber;
        this.make = make;
        this.model = model;
        this.category = category;
        this.type = type;
        this.year = year;
        this.trailer = trailer;
    }
}
