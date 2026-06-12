package com.rapassos.smart_production_planner.sales.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rapassos.smart_production_planner.sales.domain.OrderStatus;
import com.rapassos.smart_production_planner.sales.domain.SalesOrder;
import com.rapassos.smart_production_planner.sales.domain.SalesOrderRepository;

@Service
public class ProductionPlannerService {

    private final SalesOrderRepository salesOrderRepository;
    // No futuro, este ponto fará a ponte com o módulo de Manufacturing
    private final CapacityCheckerBridge capacityCheckerBridge;

    public ProductionPlannerService(SalesOrderRepository salesOrderRepository,
            CapacityCheckerBridge capacityCheckerBridge) {
        this.salesOrderRepository = salesOrderRepository;
        this.capacityCheckerBridge = capacityCheckerBridge;
    }

    @Transactional
    public SalesOrder planExecution(Long orderId) {
        SalesOrder order = salesOrderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Ordem de venda não encontrada com o ID: " + orderId));

        order.getItems().forEach(item -> {
            // Consulta a capacidade disponível no módulo de manufatura
            boolean hasCapacity = capacityCheckerBridge.hasAvailableCapacity(item.getProductId(),
                    item.getQuantity());

            if (hasCapacity) {
                item.setStatus(OrderStatus.IN_PRODUCTION);
            } else {
                item.setStatus(OrderStatus.PENDING); // Retorna ou mantém pendente por falta de
                                                     // recursos
            }
        });

        return salesOrderRepository.save(order);
    }

    // Interface funcional para manter o desacoplamento entre módulos neste momento
    public interface CapacityCheckerBridge {
        boolean hasAvailableCapacity(Long productId, Integer quantity);
    }
}
