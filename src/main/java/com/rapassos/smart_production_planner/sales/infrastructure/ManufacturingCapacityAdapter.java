package com.rapassos.smart_production_planner.sales.infrastructure;

import org.springframework.stereotype.Component;
import com.rapassos.smart_production_planner.manufacturing.application.CapacityService;
import com.rapassos.smart_production_planner.sales.application.ProductionPlannerService;

@Component
public class ManufacturingCapacityAdapter
        implements ProductionPlannerService.CapacityCheckerBridge {

    private final CapacityService capacityService;

    // O Spring Boot injeta aqui o serviço real do módulo de Manufatura
    public ManufacturingCapacityAdapter(CapacityService capacityService) {
        this.capacityService = capacityService;
    }

    @Override
    public boolean hasAvailableCapacity(Long productId, Integer quantity) {
        // Invoca a verificação dinâmica agregada no banco de dados Postgres
        return capacityService.hasCapacityForProduct(productId, quantity);
    }
}
