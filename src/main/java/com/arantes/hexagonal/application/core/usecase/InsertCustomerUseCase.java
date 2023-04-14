package com.arantes.hexagonal.application.core.usecase;

import com.arantes.hexagonal.application.core.domain.Customer;
import com.arantes.hexagonal.application.ports.in.InsertCustomerInputPort;
import com.arantes.hexagonal.application.ports.out.FindAddressByZipcodeOutputPort;
import com.arantes.hexagonal.application.ports.out.InsertCustomerOutputPort;
import com.arantes.hexagonal.application.ports.out.SendCpfForValidationOutputPort;

public class InsertCustomerUseCase implements InsertCustomerInputPort {
    private final FindAddressByZipcodeOutputPort findAddressByZipcodeOutputPort;
    private final InsertCustomerOutputPort insertCustomerOutputPort;
    private final SendCpfForValidationOutputPort sendCpfForValidationOutputPort;
    public InsertCustomerUseCase (FindAddressByZipcodeOutputPort findAddressByZipcodeOutputPort,
    InsertCustomerOutputPort insertCustomerOutputPort,
                                  SendCpfForValidationOutputPort sendCpfForValidationOutputPort) {
        this.findAddressByZipcodeOutputPort = findAddressByZipcodeOutputPort;
        this.insertCustomerOutputPort = insertCustomerOutputPort;
        this.sendCpfForValidationOutputPort = sendCpfForValidationOutputPort;
    }
    @Override
    public void insert(Customer customer, String zipcode) {
        var address = findAddressByZipcodeOutputPort.find(zipcode);
        customer.setAddress(address);
        insertCustomerOutputPort.insert(customer);
        sendCpfForValidationOutputPort.send(customer.getCpf());
    }
}
