package com.rapassos.smart_production_planner.manufacturing.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rapassos.smart_production_planner.manufacturing.controller.dto.ProductRequest;
import com.rapassos.smart_production_planner.manufacturing.controller.dto.ProductResponse;
import com.rapassos.smart_production_planner.manufacturing.domain.Product;
import com.rapassos.smart_production_planner.manufacturing.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse create(ProductRequest request) {
        if (productRepository.existsBySku(request.sku())) {
            throw new IllegalArgumentException(
                    "Já existe um produto cadastrado com o SKU: " + request.sku());
        }

        Product product = new Product();
        product.setName(request.name());
        product.setSku(request.sku().toUpperCase().trim());
        product.setLeadTimeDays(request.leadTimeDays());

        Product savedProduct = productRepository.save(product);
        return ProductResponse.fromEntity(savedProduct);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(ProductResponse::fromEntity).toList();
    }
}
