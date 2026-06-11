package com.rapassos.smart_production_planner.manufacturing.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rapassos.smart_production_planner.manufacturing.controller.dto.ResourceRequest;
import com.rapassos.smart_production_planner.manufacturing.controller.dto.ResourceResponse;
import com.rapassos.smart_production_planner.manufacturing.service.ResourceService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public ResponseEntity<ResourceResponse> create(@Valid @RequestBody ResourceRequest request) {
        ResourceResponse response = resourceService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResourceResponse>> findAll() {
        List<ResourceResponse> resources = resourceService.findAll();
        return ResponseEntity.ok(resources);
    }
}
