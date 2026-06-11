package com.rapassos.smart_production_planner.sales.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequest(
        @NotBlank(message = "O nome do cliente é obrigatório.") @Size(max = 255,
                message = "O nome deve ter no máximo 255 caracteres.") String name,

        @NotBlank(message = "O código do cliente é obrigatório.") @Size(max = 50,
                message = "O código deve ter no máximo 50 caracteres.") String code) {
}
