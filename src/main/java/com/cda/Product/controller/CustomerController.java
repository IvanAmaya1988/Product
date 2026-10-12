package com.cda.Product.controller;

import com.cda.Product.dto.CustomerRequestDto;
import com.cda.Product.dto.CustomerResponseDto;
import com.cda.Product.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> create(
            @Valid
            @RequestBody
            CustomerRequestDto dto
    ) {
        CustomerResponseDto body = customerService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getCustomers() {
        List<CustomerResponseDto> responseBody = customerService.obtenerClientes();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getById(@PathVariable Long id) {
        CustomerResponseDto body = customerService.getById(id);
        if (body == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(body);
    }
}
