package com.lissenok88.vehicle.directory.controller.rest;

import com.lissenok88.vehicle.directory.dto.VehicleDTO;
import com.lissenok88.vehicle.directory.mapper.VehicleMapper;
import com.lissenok88.vehicle.directory.model.Vehicle;
import com.lissenok88.vehicle.directory.repository.VehicleRepository;
import com.lissenok88.vehicle.directory.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping(value = VehicleRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class VehicleRestController {

    public static final String REST_URL = "/api/vehicle";
    private final VehicleRepository repository;
    private final VehicleMapper mapper;
    private final VehicleService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Vehicle> createWithLocation(@Valid @RequestBody VehicleDTO vehicleTo) {
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
    public void update(@Valid @RequestBody VehicleDTO vehicleTo, @PathVariable int id) {
        log.info("update vehicle {} with id={}", vehicleTo, id);
        assureIdConsistent(vehicleTo, id);
        repository.save(mapper.toEntity(vehicleTo));
    }

    @GetMapping("/{id}")
    public VehicleDTO get(@PathVariable long id) {
        log.info("get vehicle {}", id);
        return mapper.toTo(repository.getExisted(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@PathVariable long id) {
        log.info("delete by id={}", id);
        repository.deleteExisted(id);
    }

    @GetMapping
    public List<VehicleDTO> getAll() {
        log.info("getAll");
        return mapper.toToList(repository.findAll());
    }

    @GetMapping("/filter")
    public List<VehicleDTO> getByFilter(
            @RequestParam @Nullable String brand,
            @RequestParam @Nullable String model,
            @RequestParam @Nullable String category,
            @RequestParam @Nullable String stateNumber,
            @RequestParam @Nullable Integer year
            ) {
        log.info("getByFilter");
        return mapper.toToList(repository.findByFilter(brand, model, category, stateNumber, year));
    }
}

