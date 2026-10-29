package com.cda.Product.service;

import com.cda.Product.dto.CartItemRequestDto;
import com.cda.Product.dto.CartResponseDto;

public interface CartServiceI {

    CartResponseDto createCart();

    CartResponseDto addItemToCart(Long cartId, CartItemRequestDto requestDto);

    CartResponseDto getCartById(Long cartId);
}
