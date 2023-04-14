package com.arantes.hexagonal.adapters.out.repository;

import com.arantes.hexagonal.adapters.out.repository.mapper.CustomerEntityMapper;
import com.arantes.hexagonal.application.core.domain.Customer;
import com.arantes.hexagonal.application.ports.out.UpdateCustomerOutputPort;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateCustomerAdapter implements UpdateCustomerOutputPort {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    @Override
    public void update(Customer customer) {
        var customerEntity = customerEntityMapper.toCustomerEntity(customer);
        customerRepository.save(customerEntity);
    }
}
