package com.cda.Product.service;

import com.cda.Product.dto.CartItemRequestDto;
import com.cda.Product.dto.CartItemResponseDto;
import com.cda.Product.dto.CartResponseDto;
import com.cda.Product.exception.ConflictException;
import com.cda.Product.exception.ResourceNotFoundException;
import com.cda.Product.model.Cart;
import com.cda.Product.model.Product;
import com.cda.Product.model.ProductItem;
import com.cda.Product.repository.CartRepository;
import com.cda.Product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements CartServiceI{

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CartResponseDto createCart() {
        Cart cart = new Cart();
        Cart cartGuardado = cartRepository.save(cart);
        return mapToDto(cartGuardado);
    }


    @Override
    public CartResponseDto addItemToCart(Long cartId, CartItemRequestDto requestDto) {
       Cart cart = cartRepository.findById(cartId)
               .orElseThrow(() -> new ResourceNotFoundException("Carrito no encontrado", 404));
               //.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado")); cambio para manejo de errores

       Product product = productRepository.findById(requestDto.getProductId())
               .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado", 404));
              //.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado")); cambio para manejo de errores

        if (product.getStock() != null && product.getStock() < requestDto.getQuantity()) {
            throw new ConflictException("Stock insuficiente para el producto");
        }


       ProductItem item = new ProductItem(product, requestDto.getQuantity());

       cart.addItem(item);

       Cart cartGuardado = cartRepository.save(cart);

        return mapToDto(cartGuardado);
    }

    @Override
    public CartResponseDto getCartById(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Carrito con ID encontrado", 404));
                //.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado")); cambio manejo de errores
        return mapToDto(cart);
    }


    private CartResponseDto mapToDto(Cart cart) {
        List<CartItemResponseDto> itemDtos = new ArrayList<>();

        for (ProductItem item : cart.getCartProducts()) {
            itemDtos.add(new CartItemResponseDto(
                    item.getId(),
                    item.getProduct().getId(),
                    item.getProduct().getName(),
                    item.getProduct().getPrice(),
                    item.getCantidad()
            ));
        }

        return new CartResponseDto(cart.getId(), itemDtos);
    }
}
