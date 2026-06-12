package com.rapassos.smart_production_planner.manufacturing.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineCapacityRepository extends JpaRepository<MachineCapacity, Long> {

    // Procura todas as máquinas que possuem capacidade de produzir um determinado item
    List<MachineCapacity> findByProductId(Long productId);
}
