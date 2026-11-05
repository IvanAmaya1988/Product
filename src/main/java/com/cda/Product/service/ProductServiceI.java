package com.cda.Product.service;

import com.cda.Product.dto.ProductRequestDto;
import com.cda.Product.dto.ProductResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductServiceI {
    public ProductResponseDto getById(Long id);

    public ProductResponseDto create(ProductRequestDto dto);

    public List<ProductResponseDto> obtenerProductos();

    public List<ProductResponseDto> getByName(String name,String category);

    public List<ProductResponseDto> searchByName(String name);
    public List<ProductResponseDto> findByCategory(String category);
    public List<ProductResponseDto> findByPriceRange(BigDecimal min, BigDecimal max);
    public List<ProductResponseDto> getTopByCategory(String category);
    public Long countByCategory(String category);
}
