package com.cda.Product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product")
public class ProductController {

    @GetMapping("prueba")
    public String prueba(){
        return "ok";
    }

    @GetMapping("getProductName")
    public ResponseEntity<String> getProductName(){
        System.out.println("Consumo API");
        return new ResponseEntity<String>("Producto 1", HttpStatus.OK);
    }
}
