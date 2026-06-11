package com.rapassos.smart_production_planner.manufacturing.controller.dto;

import java.time.LocalDateTime;
import com.rapassos.smart_production_planner.manufacturing.domain.Product;

public record ProductResponse(Long id, String name, String sku, Integer leadTimeDays,
        LocalDateTime createdAt) {
    public static ProductResponse fromEntity(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getSku(),
                product.getLeadTimeDays(), product.getCreatedAt());
    }
}
