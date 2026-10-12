package com.cda.Product.service;

import com.cda.Product.dto.ProductRequestDto;
import com.cda.Product.dto.ProductResponseDto;

import java.util.List;

public interface ProductServiceI {
    public ProductResponseDto getById(Long id);

    public ProductResponseDto create(ProductRequestDto dto);

    public List<ProductResponseDto> obtenerProductos();

}
