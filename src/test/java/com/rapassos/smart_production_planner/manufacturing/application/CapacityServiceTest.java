package com.rapassos.smart_production_planner.manufacturing.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.rapassos.smart_production_planner.manufacturing.domain.MachineCapacity;
import com.rapassos.smart_production_planner.manufacturing.domain.MachineCapacityRepository;

class CapacityServiceTest {

    private MachineCapacityRepository machineCapacityRepository;
    private CapacityService capacityService;

    @BeforeEach
    void setUp() {
        machineCapacityRepository = mock(MachineCapacityRepository.class);
        capacityService = new CapacityService(machineCapacityRepository);
    }

    @Test
    void shouldReturnTrueWhenTotalCapacityIsSufficient() {
        // Arrange - Duas máquinas diferentes que juntas somam capacidade de 50 unidades
        MachineCapacity machineA = MachineCapacity.builder().machineName("Injetora A")
                .productId(100L).availableQuantity(30).build();
        MachineCapacity machineB = MachineCapacity.builder().machineName("Injetora B")
                .productId(100L).availableQuantity(20).build();

        when(machineCapacityRepository.findByProductId(100L))
                .thenReturn(List.of(machineA, machineB));

        // Act
        boolean hasCapacity = capacityService.hasCapacityForProduct(100L, 50);

        // Assert
        assertTrue(hasCapacity);
        verify(machineCapacityRepository, times(1)).findByProductId(100L);
    }

    @Test
    void shouldReturnFalseWhenTotalCapacityIsInsufficient() {
        // Arrange - Capacidade total de apenas 30 unidades para uma demanda de 35
        MachineCapacity machineA = MachineCapacity.builder().machineName("Injetora A")
                .productId(100L).availableQuantity(30).build();

        when(machineCapacityRepository.findByProductId(100L)).thenReturn(List.of(machineA));

        // Act
        boolean hasCapacity = capacityService.hasCapacityForProduct(100L, 35);

        // Assert
        assertFalse(hasCapacity);
    }
}
