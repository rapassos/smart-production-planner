package com.rapassos.smart_production_planner.manufacturing.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rapassos.smart_production_planner.manufacturing.domain.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    // Consulta customizada essencial para validações de duplicidade no cadastro
    Optional<Resource> findByCode(String code);

    boolean existsByCode(String code);
}
