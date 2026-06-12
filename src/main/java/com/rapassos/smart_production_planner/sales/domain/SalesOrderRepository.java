package com.rapassos.smart_production_planner.sales.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

    // Método para buscar um pedido pelo número de controle único, útil para integrações futuras
    Optional<SalesOrder> findByOrderNumber(String orderNumber);

    // Verificação rápida de existência para evitar duplicidade de pedidos comerciais
    boolean existsByOrderNumber(String orderNumber);
}
