package com.rapassos.smart_production_planner.manufacturing.application;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rapassos.smart_production_planner.manufacturing.domain.MachineCapacity;
import com.rapassos.smart_production_planner.manufacturing.domain.MachineCapacityRepository;

@Service
public class CapacityService {

    private final MachineCapacityRepository machineCapacityRepository;

    public CapacityService(MachineCapacityRepository machineCapacityRepository) {
        this.machineCapacityRepository = machineCapacityRepository;
    }

    @Transactional(readOnly = true)
    public boolean hasCapacityForProduct(Long productId, Integer requiredQuantity) {
        List<MachineCapacity> capacities = machineCapacityRepository.findByProductId(productId);

        // Sumariza a capacidade disponível em todas as máquinas aptas a produzir o item
        int totalAvailableQuantity =
                capacities.stream().mapToInt(MachineCapacity::getAvailableQuantity).sum();

        return totalAvailableQuantity >= requiredQuantity;
    }
}
