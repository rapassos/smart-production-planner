package com.rapassos.smart_production_planner.manufacturing.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rapassos.smart_production_planner.manufacturing.controller.dto.ResourceRequest;
import com.rapassos.smart_production_planner.manufacturing.controller.dto.ResourceResponse;
import com.rapassos.smart_production_planner.manufacturing.domain.Resource;
import com.rapassos.smart_production_planner.manufacturing.repository.ResourceRepository;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Transactional
    public ResourceResponse create(ResourceRequest request) {
        if (resourceRepository.existsByCode(request.code())) {
            throw new IllegalArgumentException(
                    "Já existe um recurso cadastrado com o código: " + request.code());
        }

        Resource resource = new Resource();
        resource.setName(request.name());
        resource.setCode(request.code().toUpperCase().trim());
        resource.setCapacityHoursPerDay(request.capacityHoursPerDay());

        Resource savedResource = resourceRepository.save(resource);
        return ResourceResponse.fromEntity(savedResource);
    }

    @Transactional(readOnly = true)
    public List<ResourceResponse> findAll() {
        return resourceRepository.findAll().stream().map(ResourceResponse::fromEntity).toList();
    }
}
