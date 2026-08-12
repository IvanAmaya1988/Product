package com.cda.Product.service;

import com.cda.Product.dto.ProductRequestDto;
import com.cda.Product.dto.ProductResponseDto;
import com.cda.Product.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    // Simular una base de datos
    private final List<Product> products = new ArrayList<>();
    // Contador para los IDs. Empieza en 1.
    private Long nextId = 1L;


    public ProductResponseDto create(ProductRequestDto dto) {
        Product product = new Product();
        product.setId(nextId);
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        products.add(product);
        nextId++;
        return toResponseDto(product);
    }

    public List<ProductResponseDto> getAll() {
       List<ProductResponseDto> responseList = new ArrayList<>();
       for (Product p : products) {
        ProductResponseDto dto = toResponseDto(p);
        responseList.add(dto);
        }
        return responseList;
    }

    public ProductResponseDto getById(Long id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return toResponseDto(p);
            }
        }
        return null;
    }

    private ProductResponseDto toResponseDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        return dto;
    }
}
