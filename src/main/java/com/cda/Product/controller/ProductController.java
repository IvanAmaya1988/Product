package com.cda.Product.controller;

import com.cda.Product.dto.ProductRequestDto;
import com.cda.Product.dto.ProductResponseDto;
import com.cda.Product.dto.ProductStockUpdateDto;
import com.cda.Product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(
            @Valid
            @RequestBody
            ProductRequestDto dto
    ) {
            ProductResponseDto body = productService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        List<ProductResponseDto> responseBody = productService.obtenerProductos();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ProductResponseDto resultado = productService.getById(id);

        /*ProductResponseDto resultado= null;   *** se anula con el manejo de Excepciones
        try {
            resultado = productService.getById(id);
        }catch(NoSuchElementException e){
            return ResponseEntity.ok("No se encuentra el producto");

        }finally {
            System.out.println("ESTA LINEA SE EJECUTA SIEMPRE");
        }
        if (resultado == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }*/
        return ResponseEntity.ok(resultado);
    }
/*
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductResponseDto> updateStock(
            @PathVariable Long id,
            @RequestBody ProductStockUpdateDto dto
            ) {
        ProductResponseDto resultado = productService.actualizarStock(id, dto.getQuantity());

        if (resultado == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {
            List<ProductResponseDto> list = productService.getAll();
        return ResponseEntity.ok(list);
    }


*/
}
