package com.lissenok88.vehicle.directory.service;

import com.lissenok88.vehicle.directory.model.VehicleTypes;
import com.lissenok88.vehicle.directory.dto.VehicleDTO;
import com.lissenok88.vehicle.directory.error.InvalidVehicleDTOException;
import com.lissenok88.vehicle.directory.mapper.VehicleMapper;
import com.lissenok88.vehicle.directory.model.Vehicle;
import com.lissenok88.vehicle.directory.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.lissenok88.vehicle.directory.util.ValidationUtil.checkNew;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleMapper mapper;
    private final VehicleRepository repository;

    public Vehicle create(VehicleDTO vehicleTo) {
        checkNew(vehicleTo);
        List<Vehicle> vehicle = repository.findByStateNumber(vehicleTo.getStateNumber());
        if(!vehicle.isEmpty()) {
            throw new InvalidVehicleDTOException("the number " + vehicleTo.getStateNumber() + " already exists");
        }
        return repository.save(mapper.toEntity(vehicleTo));
    }

    public List<String> getTypes() {
        return Stream.of(VehicleTypes.values())
                .map(VehicleTypes::name)
                .collect(Collectors.toList());
    }
}
