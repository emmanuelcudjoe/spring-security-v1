package com.cjvision.security_cj.controller;

import com.cjvision.security_cj.Entity.Product;
import com.cjvision.security_cj.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductApi {

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @PostMapping
    public Product addProduct(@RequestBody @Valid Product product){
        return productRepository.save(product);
    }
}
