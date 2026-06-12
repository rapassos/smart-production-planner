package com.rapassos.smart_production_planner.sales.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rapassos.smart_production_planner.sales.domain.OrderStatus;
import com.rapassos.smart_production_planner.sales.domain.SalesOrder;
import com.rapassos.smart_production_planner.sales.domain.SalesOrderItem;
import com.rapassos.smart_production_planner.sales.domain.SalesOrderRepository;
import com.rapassos.smart_production_planner.sales.infrastructure.dto.CreateSalesOrderRequest;

@Service
public class SalesOrderService {

    private final SalesOrderRepository salesOrderRepository;

    public SalesOrderService(SalesOrderRepository salesOrderRepository) {
        this.salesOrderRepository = salesOrderRepository;
    }

    @Transactional
    public SalesOrder createOrder(CreateSalesOrderRequest request) {
        // Regra de Negócio: Impedir duplicidade de número de controle comercial
        if (salesOrderRepository.existsByOrderNumber(request.orderNumber())) {
            throw new IllegalArgumentException(
                    "Já existe um pedido cadastrado com o número: " + request.orderNumber());
        }

        // Construindo a Raiz de Agregação (SalesOrder)
        SalesOrder order = SalesOrder.builder().orderNumber(request.orderNumber())
                .customerId(request.customerId()).deliveryDate(request.deliveryDate()).build();

        // Mapeando e Vinculando os Itens de forma bidirecional segura
        request.items().forEach(itemRequest -> {
            SalesOrderItem item = SalesOrderItem.builder().productId(itemRequest.productId())
                    .quantity(itemRequest.quantity()).status(OrderStatus.PENDING).build();
            order.addItem(item);
        });

        return salesOrderRepository.save(order);
    }
}
