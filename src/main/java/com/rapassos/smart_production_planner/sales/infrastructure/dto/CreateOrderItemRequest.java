package com.rapassos.smart_production_planner.sales.infrastructure.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateOrderItemRequest(
        @NotNull(message = "O ID do produto é obrigatório.") Long productId,

        @NotNull(message = "A quantidade do produto deve ser informada.") @Positive(
                message = "A quantidade deve ser um número maior que zero.") Integer quantity) {
}
