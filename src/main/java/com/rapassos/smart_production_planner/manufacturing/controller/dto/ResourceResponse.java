package com.rapassos.smart_production_planner.manufacturing.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.rapassos.smart_production_planner.manufacturing.domain.Resource;

public record ResourceResponse(Long id, String name, String code, BigDecimal capacityHoursPerDay,
        LocalDateTime createdAt) {
    public static ResourceResponse fromEntity(Resource resource) {
        return new ResourceResponse(resource.getId(), resource.getName(), resource.getCode(),
                resource.getCapacityHoursPerDay(), resource.getCreatedAt());
    }
}
