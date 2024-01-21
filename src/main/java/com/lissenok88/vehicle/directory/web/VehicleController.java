package com.lissenok88.vehicle.directory.web;

import com.lissenok88.vehicle.directory.mapper.VehicleMapper;
import com.lissenok88.vehicle.directory.model.Vehicle;
import com.lissenok88.vehicle.directory.repository.VehicleRepository;
import com.lissenok88.vehicle.directory.service.VehicleService;
import com.lissenok88.vehicle.directory.to.VehicleTo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.lissenok88.vehicle.directory.util.ValidationUtil.*;

@Slf4j
@RestController
@RequestMapping(value = VehicleController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class VehicleController {

    public static final String REST_URL = "/api/vehicle";

    private VehicleRepository repository;
    private VehicleMapper mapper;
    private VehicleService service;

    @Autowired
    VehicleController(VehicleRepository repository, VehicleMapper mapper, VehicleService service) {
        this.repository = repository;
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Vehicle> createWithLocation(@Valid @RequestBody VehicleTo vehicleTo) {
        log.info("create vehicle {}", vehicleTo);
        Vehicle created = service.create(vehicleTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void update(@Valid @RequestBody VehicleTo vehicleTo, @PathVariable int id) {
        log.info("update vehicle {} with id={}", vehicleTo, id);
        assureIdConsistent(vehicleTo, id);
        repository.save(mapper.toEntity(vehicleTo));
    }

    @GetMapping("/{id}")
    public VehicleTo get(@PathVariable long id) {
        log.info("get vehicle {}", id);
        return mapper.toTo(repository.getExisted(id));
    }

    @GetMapping("/by-category")
    public List<VehicleTo> getAllByCategory(@RequestParam String category) {
        log.info("get vehicles by category {}", category);
        return mapper.toToList(repository.findAllByCategory(category));
    }

    @GetMapping("/by-model")
    public List<VehicleTo> getAllByModel(@RequestParam String model) {
        log.info("get vehicles by model {}", model);
        return mapper.toToList(repository.findAllByModel(model));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@PathVariable long id) {
        log.info("delete by id={}", id);
        repository.deleteExisted(id);
    }

    @GetMapping
    public List<VehicleTo> getAll() {
        log.info("getAll");
        return mapper.toToList(repository.findAll());
    }

    @GetMapping("/filter")
    public List<VehicleTo> getByFilter(
            @RequestParam @Nullable String make,
            @RequestParam @Nullable String model,
            @RequestParam @Nullable String category,
            @RequestParam @Nullable String stateNumber,
            @RequestParam @Nullable Integer year
            ) {
        log.info("getByFilter");
        return mapper.toToList(repository.findByFilter(make, model, category, stateNumber, year));
    }
}

