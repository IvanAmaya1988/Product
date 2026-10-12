package com.cda.Product.service;

import com.cda.Product.dto.CustomerRequestDto;
import com.cda.Product.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerServiceI {
    public CustomerResponseDto getById(Long id);

    public CustomerResponseDto create(CustomerRequestDto dto);

    public List<CustomerResponseDto> obtenerClientes();
}
