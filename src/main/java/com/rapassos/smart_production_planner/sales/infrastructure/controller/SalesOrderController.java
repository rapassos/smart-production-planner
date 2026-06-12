package com.rapassos.smart_production_planner.sales.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rapassos.smart_production_planner.sales.application.SalesOrderService;
import com.rapassos.smart_production_planner.sales.domain.SalesOrder;
import com.rapassos.smart_production_planner.sales.infrastructure.dto.CreateSalesOrderRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/sales-orders")
public class SalesOrderController {

    private final SalesOrderService salesOrderService;

    public SalesOrderController(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
    }

    @PostMapping
    public ResponseEntity<SalesOrder> createOrder(
            @Valid @RequestBody CreateSalesOrderRequest request) {
        SalesOrder createdOrder = salesOrderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
}
