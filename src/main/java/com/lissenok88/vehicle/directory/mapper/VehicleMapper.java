package com.lissenok88.vehicle.directory.mapper;

import com.lissenok88.vehicle.directory.dto.VehicleDTO;
import com.lissenok88.vehicle.directory.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    Vehicle toEntity(VehicleDTO to);

    List<Vehicle> toEntityList(Collection<VehicleDTO> tos);

    Vehicle updateFromTo(VehicleDTO to, @MappingTarget Vehicle entity);

    VehicleDTO toTo(Vehicle entity);

    List<VehicleDTO> toToList(Collection<Vehicle> entities);
}
