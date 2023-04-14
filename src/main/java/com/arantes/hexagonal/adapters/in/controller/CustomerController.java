package com.arantes.hexagonal.adapters.in.controller;

import com.arantes.hexagonal.adapters.in.controller.mapper.CustomerMapper;
import com.arantes.hexagonal.adapters.in.controller.request.CustomerRequest;
import com.arantes.hexagonal.adapters.in.controller.response.CustomerResponse;
import com.arantes.hexagonal.application.core.domain.Customer;
import com.arantes.hexagonal.application.ports.in.DeleteCustomerByIdInputPort;
import com.arantes.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import com.arantes.hexagonal.application.ports.in.InsertCustomerInputPort;
import com.arantes.hexagonal.application.ports.in.UpdateCustomerInputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private InsertCustomerInputPort insertCustomerInputPort;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private FindCustomerByIdInputPort findCustomerByIdInputPort;
    @Autowired
    UpdateCustomerInputPort updateCustomerInputPort;

    @Autowired
    DeleteCustomerByIdInputPort deleteCustomerByIdInputPort;

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerRequest customerRequest) {
        insertCustomerInputPort.insert(customerMapper.toCustomer(customerRequest), customerRequest.getZipcode());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable final String id) {
        var customer = findCustomerByIdInputPort.find(id);
        var customerResponse = customerMapper.toCustomerResponse(customer);
        return ResponseEntity.ok().body(customerResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final String id, @RequestBody CustomerRequest customerRequest) {
        Customer customer = customerMapper.toCustomer(customerRequest);
        customer.setId(id);
        updateCustomerInputPort.update(customer, customerRequest.getZipcode());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final String id) {
        deleteCustomerByIdInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
