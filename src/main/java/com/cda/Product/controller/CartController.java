package com.cda.Product.controller;

import com.cda.Product.dto.CartItemRequestDto;
import com.cda.Product.dto.CartResponseDto;
import com.cda.Product.service.CartService;
import com.cda.Product.service.CartServiceI;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping()
    public ResponseEntity<CartResponseDto> createCart() {
        return ResponseEntity.ok(cartService.createCart());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCartById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(cartService.getCartById(id));
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartResponseDto> addItemToCart(
            @PathVariable("cartId") Long cartId,
            @Valid @RequestBody CartItemRequestDto requestDto) {

        return ResponseEntity.ok(cartService.addItemToCart(cartId, requestDto));
    }

}
