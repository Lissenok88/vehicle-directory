package com.lissenok88.vehicle.directory.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class VehicleDTO extends BaseDTO {

    @NotBlank
    String stateNumber;

    @NotBlank
    String brand;

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

    public VehicleDTO(Long id, String stateNumber, String brand, String model, String category, String type, String year, String trailer) {
        super(id);
        this.stateNumber = stateNumber;
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.type = type;
        this.year = year;
        this.trailer = trailer;
    }
}
