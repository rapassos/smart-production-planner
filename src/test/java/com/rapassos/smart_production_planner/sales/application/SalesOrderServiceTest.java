package com.rapassos.smart_production_planner.sales.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.rapassos.smart_production_planner.sales.domain.SalesOrder;
import com.rapassos.smart_production_planner.sales.domain.SalesOrderRepository;
import com.rapassos.smart_production_planner.sales.infrastructure.dto.CreateOrderItemRequest;
import com.rapassos.smart_production_planner.sales.infrastructure.dto.CreateSalesOrderRequest;

class SalesOrderServiceTest {

    private SalesOrderRepository salesOrderRepository;
    private SalesOrderService salesOrderService;

    @BeforeEach
    void setUp() {
        salesOrderRepository = mock(SalesOrderRepository.class);
        salesOrderService = new SalesOrderService(salesOrderRepository);
    }

    @Test
    void shouldCreateSalesOrderWithSuccess() {
        // Arrange
        CreateOrderItemRequest itemRequest = new CreateOrderItemRequest(1L, 10);
        CreateSalesOrderRequest request = new CreateSalesOrderRequest("PO-2026-001", 5L,
                LocalDateTime.now().plusDays(5), List.of(itemRequest));

        when(salesOrderRepository.existsByOrderNumber(request.orderNumber())).thenReturn(false);
        when(salesOrderRepository.save(any(SalesOrder.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        SalesOrder savedOrder = salesOrderService.createOrder(request);

        // Assert
        assertNotNull(savedOrder);
        assertEquals("PO-2026-001", savedOrder.getOrderNumber());
        assertEquals(5L, savedOrder.getCustomerId());
        assertEquals(1, savedOrder.getItems().size());
        verify(salesOrderRepository, times(1)).save(any(SalesOrder.class));
    }

    @Test
    void shouldThrowExceptionWhenOrderNumberAlreadyExists() {
        // Arrange
        CreateOrderItemRequest itemRequest = new CreateOrderItemRequest(1L, 10);
        CreateSalesOrderRequest request = new CreateSalesOrderRequest("PO-DUPLICADO", 5L,
                LocalDateTime.now().plusDays(5), List.of(itemRequest));

        when(salesOrderRepository.existsByOrderNumber(request.orderNumber())).thenReturn(true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            salesOrderService.createOrder(request);
        });

        assertEquals("Já existe um pedido cadastrado com o número: PO-DUPLICADO",
                exception.getMessage());
        verify(salesOrderRepository, never()).save(any(SalesOrder.class));
    }
}
