package com.cda.Product.controller;

import com.cda.Product.dto.CustomerRequestDto;
import com.cda.Product.dto.CustomerResponseDto;
import com.cda.Product.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/api/v1/customer"))
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerRequestDto customer) {

        customerService.createCustomer(customer);

        return ResponseEntity.ok("Customer creado con exito");
    }
/*    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(
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
*/
}

