package com.lissenok88.vehicle.directory.service;

import com.lissenok88.vehicle.directory.error.IllegalRequestDataException;
import com.lissenok88.vehicle.directory.mapper.VehicleMapper;
import com.lissenok88.vehicle.directory.model.Vehicle;
import com.lissenok88.vehicle.directory.repository.VehicleRepository;
import com.lissenok88.vehicle.directory.to.VehicleTo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lissenok88.vehicle.directory.util.ValidationUtil.checkNew;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private VehicleMapper mapper;
    private VehicleRepository repository;

    @Autowired
    VehicleService(VehicleMapper mapper, VehicleRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Vehicle create(VehicleTo vehicleTo) {
        checkNew(vehicleTo);
        List<Vehicle> vehicle = repository.findByStateNumber(vehicleTo.getStateNumber());
        if(!vehicle.isEmpty()) {
            throw new IllegalRequestDataException("the number " + vehicleTo.getStateNumber() + " already exists");
        }
        return repository.save(mapper.toEntity(vehicleTo));
    }
}
