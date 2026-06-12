package com.rapassos.smart_production_planner.sales.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.rapassos.smart_production_planner.sales.domain.OrderStatus;
import com.rapassos.smart_production_planner.sales.domain.SalesOrder;
import com.rapassos.smart_production_planner.sales.domain.SalesOrderItem;
import com.rapassos.smart_production_planner.sales.domain.SalesOrderRepository;

class ProductionPlannerServiceTest {

    private SalesOrderRepository salesOrderRepository;
    private ProductionPlannerService.CapacityCheckerBridge capacityCheckerBridge;
    private ProductionPlannerService productionPlannerService;

    @BeforeEach
    void setUp() {
        salesOrderRepository = mock(SalesOrderRepository.class);
        capacityCheckerBridge = mock(ProductionPlannerService.CapacityCheckerBridge.class);
        productionPlannerService =
                new ProductionPlannerService(salesOrderRepository, capacityCheckerBridge);
    }

    @Test
    void shouldMoveStatusToInProductionWhenCapacityIsAvailable() {
        // Arrange
        SalesOrderItem item = SalesOrderItem.builder().id(1L).productId(10L).quantity(5)
                .status(OrderStatus.PENDING).build();
        SalesOrder order = SalesOrder.builder().id(1L).orderNumber("PO-001")
                .items(new ArrayList<>(List.of(item))).build();
        item.setSalesOrder(order);

        when(salesOrderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(capacityCheckerBridge.hasAvailableCapacity(10L, 5)).thenReturn(true);
        when(salesOrderRepository.save(any(SalesOrder.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        SalesOrder updatedOrder = productionPlannerService.planExecution(1L);

        // Assert
        assertEquals(OrderStatus.IN_PRODUCTION, updatedOrder.getItems().get(0).getStatus());
        verify(salesOrderRepository, times(1)).save(order);
    }

    @Test
    void shouldKeepStatusPendingWhenCapacityIsInsufficient() {
        // Arrange
        SalesOrderItem item = SalesOrderItem.builder().id(1L).productId(10L).quantity(100)
                .status(OrderStatus.PENDING).build();
        SalesOrder order = SalesOrder.builder().id(1L).orderNumber("PO-002")
                .items(new ArrayList<>(List.of(item))).build();
        item.setSalesOrder(order);

        when(salesOrderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(capacityCheckerBridge.hasAvailableCapacity(10L, 100)).thenReturn(false);
        when(salesOrderRepository.save(any(SalesOrder.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        SalesOrder updatedOrder = productionPlannerService.planExecution(1L);

        // Assert
        assertEquals(OrderStatus.PENDING, updatedOrder.getItems().get(0).getStatus());
        verify(salesOrderRepository, times(1)).save(order);
    }
}
