package com.arantes.hexagonal.application.ports.out;

import com.arantes.hexagonal.application.core.domain.Address;

public interface FindAddressByZipcodeOutputPort {
    Address find(String zipCode);
}
