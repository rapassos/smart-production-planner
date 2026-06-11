package com.rapassos.smart_production_planner.manufacturing.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRequest(
        @NotBlank(message = "O nome do produto é obrigatório.") @Size(max = 255,
                message = "O nome deve ter no máximo 255 caracteres.") String name,

        @NotBlank(message = "O SKU do produto é obrigatório.") @Size(max = 100,
                message = "O SKU deve ter no máximo 100 caracteres.") String sku,

        @NotNull(message = "O lead time em dias é obrigatório.") @Min(value = 0,
                message = "O lead time não pode ser negativo.") Integer leadTimeDays) {
}
