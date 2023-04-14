package com.arantes.hexagonal.application.core.usecase;


import com.arantes.hexagonal.application.core.domain.Customer;
import com.arantes.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import com.arantes.hexagonal.application.ports.out.FindCustomerByIdOutputPort;

public class FindCustomerByIdUseCase implements FindCustomerByIdInputPort {
    private final FindCustomerByIdOutputPort findCustomerByIdOutputport;

    public FindCustomerByIdUseCase(FindCustomerByIdOutputPort findCustomerByIdOutputport) {
        this.findCustomerByIdOutputport = findCustomerByIdOutputport;
    }
@Override
    public Customer find(String id) {
        return findCustomerByIdOutputport.find(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

}
