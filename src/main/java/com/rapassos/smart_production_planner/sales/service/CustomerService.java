package com.rapassos.smart_production_planner.sales.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rapassos.smart_production_planner.sales.controller.dto.CustomerRequest;
import com.rapassos.smart_production_planner.sales.controller.dto.CustomerResponse;
import com.rapassos.smart_production_planner.sales.domain.Customer;
import com.rapassos.smart_production_planner.sales.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerResponse create(CustomerRequest request) {
        if (customerRepository.existsByCode(request.code())) {
            throw new IllegalArgumentException(
                    "Já existe um cliente cadastrado com o código: " + request.code());
        }

        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setCode(request.code().toUpperCase().trim());

        Customer savedCustomer = customerRepository.save(customer);
        return CustomerResponse.fromEntity(savedCustomer);
    }

    @Transactional(readOnly = true)
    public List<CustomerResponse> findAll() {
        return customerRepository.findAll().stream().map(CustomerResponse::fromEntity).toList();
    }
}
