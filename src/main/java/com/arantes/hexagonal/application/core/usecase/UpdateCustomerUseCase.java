package com.arantes.hexagonal.application.core.usecase;

import com.arantes.hexagonal.application.core.domain.Customer;
import com.arantes.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import com.arantes.hexagonal.application.ports.in.UpdateCustomerInputPort;
import com.arantes.hexagonal.application.ports.out.FindAddressByZipcodeOutputPort;
import com.arantes.hexagonal.application.ports.out.UpdateCustomerOutputPort;

public class UpdateCustomerUseCase implements UpdateCustomerInputPort {
    private final FindCustomerByIdInputPort findCustomerByIdInputPort;
    private final FindAddressByZipcodeOutputPort findAddressByZipcodeOutputPort;
    private final UpdateCustomerOutputPort updateCustomerOutputPort;

    public UpdateCustomerUseCase(FindCustomerByIdInputPort findCustomerByIdInputPort,
                                 FindAddressByZipcodeOutputPort findAddressByZipcodeOutputPort,
                                 UpdateCustomerOutputPort updateCustomerOutputPort) {
        this.findCustomerByIdInputPort = findCustomerByIdInputPort;
        this.findAddressByZipcodeOutputPort = findAddressByZipcodeOutputPort;
        this.updateCustomerOutputPort = updateCustomerOutputPort;
    }
    @Override
    public void update(Customer customer, String zipCode) {
        findCustomerByIdInputPort.find(customer.getId());
        var address = findAddressByZipcodeOutputPort.find(zipCode);
        customer.setAddress(address);
        updateCustomerOutputPort.update(customer);
    }
}
