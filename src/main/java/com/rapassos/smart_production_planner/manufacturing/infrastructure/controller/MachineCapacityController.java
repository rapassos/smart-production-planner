package com.rapassos.smart_production_planner.manufacturing.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rapassos.smart_production_planner.manufacturing.domain.MachineCapacity;
import com.rapassos.smart_production_planner.manufacturing.domain.MachineCapacityRepository;

@RestController
@RequestMapping("/api/v1/manufacturing/capacities")
public class MachineCapacityController {

    private final MachineCapacityRepository machineCapacityRepository;

    public MachineCapacityController(MachineCapacityRepository machineCapacityRepository) {
        this.machineCapacityRepository = machineCapacityRepository;
    }

    @PostMapping
    public ResponseEntity<MachineCapacity> createCapacity(@RequestBody MachineCapacity capacity) {
        MachineCapacity savedCapacity = machineCapacityRepository.save(capacity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCapacity);
    }
}
