package com.lissenok88.vehicle.directory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends BaseEntity {

    @Column(name = "state_number", nullable = false)
    @NotBlank
    private String stateNumber;

    @Column(name = "make", nullable = false)
    @NotBlank
    private String make;

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

    public Vehicle(Long id, String stateNumber, String make, String model, String category, String type, String year, String trailer) {
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
