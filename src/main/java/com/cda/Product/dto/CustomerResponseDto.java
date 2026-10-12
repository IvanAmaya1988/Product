package com.cda.Product.dto;

public record CustomerResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone
) {}
