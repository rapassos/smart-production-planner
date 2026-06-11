package com.rapassos.smart_production_planner.manufacturing.controller.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ResourceRequest(
        @NotBlank(message = "O nome do recurso é obrigatório.") @Size(max = 255,
                message = "O nome deve ter no máximo 255 caracteres.") String name,

        @NotBlank(message = "O código do recurso é obrigatório.") @Size(max = 50,
                message = "O código deve ter no máximo 50 caracteres.") String code,

        @NotNull(message = "A capacidade diária em horas é obrigatória.") @DecimalMin(
                value = "0.01",
                message = "A capacidade deve ser maior que zero.") BigDecimal capacityHoursPerDay) {
}
