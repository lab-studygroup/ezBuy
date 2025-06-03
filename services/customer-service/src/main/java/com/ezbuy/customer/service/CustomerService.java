package com.ezbuy.customer.service;

import com.ezbuy.customer.dto.CustomerCreateRequestDTO;
import com.ezbuy.customer.dto.CustomerDTO;
import com.ezbuy.customer.mapper.CustomerMapper;
import com.ezbuy.customer.model.Customer;
import com.ezbuy.customer.repository.CustomerRepository;
import com.ezbuy.customer.service.CustomerService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDTO create(CustomerCreateRequestDTO customerCreateRequestDTO) {
        Customer customer = CustomerMapper.toEntity(customerCreateRequestDTO);
        return CustomerMapper.toResponse(customerRepository.save(customer));
    }

    public CustomerDTO getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return CustomerMapper.toResponse(customer);
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public CustomerDTO update(Long id, CustomerCreateRequestDTO dto) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found: " + id));

        Customer updated = CustomerMapper.toEntity(dto);
        updated.setId(existing.getId());

        return CustomerMapper.toResponse(customerRepository.save(updated));
    }

    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found");
        }
        customerRepository.deleteById(id);
    }
}
