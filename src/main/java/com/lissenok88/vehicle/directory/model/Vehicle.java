package com.lissenok88.vehicle.directory.model;

import com.lissenok88.vehicle.directory.model.base.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@jakarta.persistence.Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends Entity {

    @Column(name = "state_number", nullable = false)
    @NotBlank
    private String stateNumber;

    @Column(name = "brand", nullable = false)
    @NotBlank
    private String brand;

    @Column(name = "model", nullable = false)
    @NotBlank
    private String model;

    @Column(name = "category", nullable = false)
    @NotBlank
    private String category;

    @Column(name = "type", nullable = false)
    @NotBlank
    private String type;

    @Column(name = "year")
    @NotBlank
    private String year;

    @Column(name = "trailer", nullable = false)
    @NotBlank
    private  String trailer;

    public Vehicle(Long id, String stateNumber, String brand, String model, String category, String type, String year, String trailer) {
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
