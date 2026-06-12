package com.rapassos.smart_production_planner.sales.infrastructure.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.rapassos.smart_production_planner.sales.application.ProductionPlannerService;
import com.rapassos.smart_production_planner.sales.application.SalesOrderService;
import com.rapassos.smart_production_planner.sales.domain.SalesOrder;
import com.rapassos.smart_production_planner.sales.infrastructure.dto.CreateOrderItemRequest;
import com.rapassos.smart_production_planner.sales.infrastructure.dto.CreateSalesOrderRequest;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(SalesOrderController.class)
class SalesOrderControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockitoBean
        private SalesOrderService salesOrderService;

        @MockitoBean // Injetando o novo mock para satisfazer o construtor atualizado
        private ProductionPlannerService productionPlannerService;

        @Test
        void shouldReturnCreatedWhenPayloadIsValid() throws Exception {
                // Arrange
                CreateOrderItemRequest itemRequest = new CreateOrderItemRequest(1L, 5);
                CreateSalesOrderRequest request = new CreateSalesOrderRequest("PO-100", 2L,
                                LocalDateTime.now().plusDays(10), List.of(itemRequest));

                SalesOrder mockOrder = SalesOrder.builder().id(1L).orderNumber("PO-100")
                                .customerId(2L).build();

                when(salesOrderService.createOrder(any(CreateSalesOrderRequest.class)))
                                .thenReturn(mockOrder);

                // Act & Assert
                mockMvc.perform(post("/api/v1/sales-orders").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id").value(1L))
                                .andExpect(jsonPath("$.orderNumber").value("PO-100"));
        }

        @Test
        void shouldReturnBadRequestWhenPayloadIsInvalid() throws Exception {
                // Arrange
                CreateSalesOrderRequest invalidRequest = new CreateSalesOrderRequest("", 2L,
                                LocalDateTime.now().plusDays(10), List.of());

                // Act & Assert
                mockMvc.perform(post("/api/v1/sales-orders").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidRequest)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnOkWhenPlanningExecutionIsTriggered() throws Exception {
                // Arrange
                SalesOrder mockPlannedOrder =
                                SalesOrder.builder().id(99L).orderNumber("PO-PLAN-TEST").build();

                when(productionPlannerService.planExecution(99L)).thenReturn(mockPlannedOrder);

                // Act & Assert
                mockMvc.perform(post("/api/v1/sales-orders/99/plan")
                                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(99L))
                                .andExpect(jsonPath("$.orderNumber").value("PO-PLAN-TEST"));
        }
}
