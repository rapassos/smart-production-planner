package com.rapassos.smart_production_planner.manufacturing.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Impede a
                                                                             // substituição pelo
                                                                             // banco em memória
class MachineCapacityRepositoryTest {

    @Autowired
    private MachineCapacityRepository repository;

    @Test
    void shouldFindCapacityByProductIdWithSuccess() {
        // Arrange
        MachineCapacity capacity =
                MachineCapacity.builder().machineName("Injetora Industrial H-100").productId(10L)
                        .availableQuantity(75).build();
        repository.save(capacity);

        // Act
        List<MachineCapacity> result = repository.findByProductId(10L);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Injetora Industrial H-100", result.get(0).getMachineName());
        assertEquals(75, result.get(0).getAvailableQuantity());
    }
}
