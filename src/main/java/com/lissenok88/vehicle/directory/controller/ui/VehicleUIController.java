package com.lissenok88.vehicle.directory.controller.ui;

import com.lissenok88.vehicle.directory.mapper.VehicleMapper;
import com.lissenok88.vehicle.directory.repository.VehicleRepository;
import com.lissenok88.vehicle.directory.service.VehicleService;
import com.lissenok88.vehicle.directory.dto.VehicleDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(VehicleUIController.VEHICLE_URL)
@RequiredArgsConstructor
public class VehicleUIController {
    static final String VEHICLE_URL = "/ui/vehicle";
    private final VehicleRepository repository;
    private final VehicleMapper mapper;
    private final VehicleService service;

    @GetMapping("/{id}")
    public String get(@PathVariable long id, Model model) {
        log.info("get vehicle {}", id);
        VehicleDTO vehicleTo = mapper.toTo(repository.getExisted(id));
        model.addAttribute("vehicleTo", vehicleTo);
        return "vehicles";
    }

    @GetMapping
    public String getAll(Model model) {
        log.info("getAll");
        List<VehicleDTO> vehicleTos = mapper.toToList(repository.findAll());
        model.addAttribute("vehicleTos", vehicleTos);
        return "vehicles";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable long id, Model model) {
        log.info("get vehicle {}", id);
        VehicleDTO vehicleTo = mapper.toTo(repository.getExisted(id));
        model.addAttribute("types", service.getTypes());
        model.addAttribute("vehicleTo", vehicleTo);
        return "vehicle-edit";
    }

    @GetMapping("/new")
    public String showAdd(Model model) {
        log.info("new vehicle");
        model.addAttribute("types", service.getTypes());
        return "vehicle-add";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable long id, @Valid @ModelAttribute("vehicleTo") VehicleDTO vehicleTo, Model model) {
        log.info("update {} with id={}", vehicleTo, vehicleTo.id());
        repository.save(mapper.toEntity(vehicleTo));
        return "redirect:/ui/vehicle";
    }

    @PostMapping("/new")
    public String create(@Valid @ModelAttribute("vehicleTo") VehicleDTO vehicleTo, Model model) {
        log.info("create {}", vehicleTo);
        service.create(vehicleTo);
        return "redirect:/ui/vehicle";
    }

    @GetMapping("/filter")
    public String getByFilter(
            @RequestParam @Nullable String brand,
            @RequestParam @Nullable String models,
            @RequestParam @Nullable String category,
            @RequestParam @Nullable String stateNumber,
            @RequestParam @Nullable Integer year,
            Model model
    ) {
        log.info("getByFilter");
        List<VehicleDTO> vehicleTos = mapper.toToList(repository.findByFilter(brand, models, category, stateNumber, year));
        model.addAttribute("vehicleTos", vehicleTos);
        return "vehicles";
    }
}
