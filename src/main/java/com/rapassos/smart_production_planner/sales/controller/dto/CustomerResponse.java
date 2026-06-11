package com.rapassos.smart_production_planner.sales.controller.dto;

import java.time.LocalDateTime;
import com.rapassos.smart_production_planner.sales.domain.Customer;

public record CustomerResponse(Long id, String name, String code, LocalDateTime createdAt) {
    public static CustomerResponse fromEntity(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getCode(),
                customer.getCreatedAt());
    }
}
