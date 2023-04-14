package com.arantes.hexagonal.config;

import com.arantes.hexagonal.adapters.out.repository.DeleteCustomerByIdAdapter;
import com.arantes.hexagonal.application.core.usecase.DeleteCustomerByIdUseCase;
import com.arantes.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteCustomerByIdConfig {
    @Bean
    public DeleteCustomerByIdUseCase deleteCustomerByIUseCase(
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            DeleteCustomerByIdAdapter deleteCustomerByIdAdapter) {

        return new DeleteCustomerByIdUseCase(findCustomerByIdUseCase, deleteCustomerByIdAdapter);
    }
}
