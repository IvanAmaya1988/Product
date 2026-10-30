package com.cda.Product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequestDto (

        @NotBlank(message = "El nombre no puede estar vacío")
        String name,
        @NotBlank(message = "Descripción no puede estar vacía")
        @Size(min=3,max=100, message = "Descripción debe ser entre 3 y 100 caracteres")
        String description,
        @NotNull(message = "El precio no puede estar vacío")
        @Positive(message = "El precio debe ser mayor a cero")
        BigDecimal price,
        @NotNull(message = "El stock es obligatorio")
        @PositiveOrZero(message = "El stock no puede ser negativo")
        Integer stock){}
/*
    public String getName() { return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}*/
