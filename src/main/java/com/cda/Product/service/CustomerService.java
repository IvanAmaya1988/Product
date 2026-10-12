package com.cda.Product.service;

import com.cda.Product.dto.CustomerRequestDto;
import com.cda.Product.dto.CustomerResponseDto;
import com.cda.Product.model.Customer;
import com.cda.Product.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements CustomerServiceI{

       CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public CustomerResponseDto create(CustomerRequestDto dto){
        Customer customer = new Customer();
        customer.setFirstName(dto.firstName());
        customer.setLastName(dto.lastName());
        customer.setEmail(dto.email());
        customer.setPhone(dto.phone());

        return getClienteDto(repository.save(customer));
    }

    public CustomerResponseDto getById(Long id) {
        return getClienteDto(repository.findById(id).get());
    }

    public List<CustomerResponseDto> obtenerClientes() {
        List<CustomerResponseDto> responseList = new ArrayList<>();

        for (Customer c : repository.findAll()) {
            CustomerResponseDto dto = getClienteDto(c);
            responseList.add(dto);
        }

        return responseList;
    }

    private CustomerResponseDto getClienteDto(Customer cliente){
        return new CustomerResponseDto(
                cliente.getId(),
                cliente.getFirstName(),
                cliente.getLastName(),
                cliente.getEmail(),
                cliente.getPhone()
        );
    }
}
