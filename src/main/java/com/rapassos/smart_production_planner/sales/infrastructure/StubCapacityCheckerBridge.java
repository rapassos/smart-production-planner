package com.rapassos.smart_production_planner.sales.infrastructure;

import org.springframework.stereotype.Component;
import com.rapassos.smart_production_planner.sales.application.ProductionPlannerService;

@Component
public class StubCapacityCheckerBridge implements ProductionPlannerService.CapacityCheckerBridge {

    @Override
    public boolean hasAvailableCapacity(Long productId, Integer quantity) {
        // Stub temporário para viabilizar o bootstrap do ApplicationContext.
        // Nas próximas tarefas, este componente consultará o módulo de manufatura.
        return true;
    }
}
