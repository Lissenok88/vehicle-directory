package com.lissenok88.vehicle.directory.mapper;

import com.lissenok88.vehicle.directory.model.Vehicle;
import com.lissenok88.vehicle.directory.to.VehicleTo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    Vehicle toEntity(VehicleTo to);

    List<Vehicle> toEntityList(Collection<VehicleTo> tos);

    Vehicle updateFromTo(VehicleTo to, @MappingTarget Vehicle entity);

    VehicleTo toTo(Vehicle entity);

    List<VehicleTo> toToList(Collection<Vehicle> entities);
}
