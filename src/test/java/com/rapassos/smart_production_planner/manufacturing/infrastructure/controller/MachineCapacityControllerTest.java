package com.rapassos.smart_production_planner.manufacturing.infrastructure.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.rapassos.smart_production_planner.manufacturing.domain.MachineCapacity;
import com.rapassos.smart_production_planner.manufacturing.domain.MachineCapacityRepository;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(MachineCapacityController.class)
class MachineCapacityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean // Compatibilidade total Spring Boot 3.4+
    private MachineCapacityRepository machineCapacityRepository;

    @Test
    void shouldReturnCreatedWhenCapacityIsSuccessfullyRegistered() throws Exception {
        // Arrange
        MachineCapacity mockCapacity =
                MachineCapacity.builder().id(1L).machineName("Prensa Hidráulica P-500")
                        .productId(20L).availableQuantity(150).build();

        when(machineCapacityRepository.save(any(MachineCapacity.class))).thenReturn(mockCapacity);

        // Act & Assert
        mockMvc.perform(
                post("/api/v1/manufacturing/capacities").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockCapacity)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.machineName").value("Prensa Hidráulica P-500"))
                .andExpect(jsonPath("$.availableQuantity").value(150));
    }
}
