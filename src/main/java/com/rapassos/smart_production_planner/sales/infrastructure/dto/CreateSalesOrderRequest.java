package com.rapassos.smart_production_planner.sales.infrastructure.dto;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateSalesOrderRequest(
        @NotBlank(message = "O número do pedido não pode estar em branco.") String orderNumber,

        @NotNull(message = "O ID do cliente é obrigatório.") Long customerId,

        @NotNull(message = "A data de entrega estimada deve ser informada.") @Future(
                message = "A data de entrega deve ser uma data futura.") LocalDateTime deliveryDate,

        @NotEmpty(
                message = "O pedido deve conter pelo menos um item.") List<CreateOrderItemRequest> items) {
}
