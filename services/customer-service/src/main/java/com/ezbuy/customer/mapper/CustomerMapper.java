package com.ezbuy.customer.mapper;

import com.ezbuy.customer.dto.CustomerCreateRequestDTO;
import com.ezbuy.customer.dto.CustomerDTO;
import com.ezbuy.customer.model.Customer;

public class CustomerMapper {
    

    public static Customer toEntity(CustomerCreateRequestDTO dto) {
        return Customer.builder()
                .name(dto.name())
                .email(dto.email())
                .build();
    }

    public static CustomerDTO toResponse(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail()
        );
    }
}
