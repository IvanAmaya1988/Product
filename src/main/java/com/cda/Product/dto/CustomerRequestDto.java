package com.cda.Product.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequestDto(
        @NotBlank(message = "El primer nombre es obligatorio")
        @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
        String firstName,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(min = 2, message = "El apellido debe tener al menos 2 caracteres")
        String lastName,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Debe proporcionar un formato de email válido")
        String email,

        @Size(min = 8, message = "El teléfono debe tener al menos 8 dígitos")
        String phone
){}