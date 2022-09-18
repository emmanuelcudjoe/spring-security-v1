package com.cjvision.security_cj.controller;

import com.cjvision.security_cj.Entity.Product;
import com.cjvision.security_cj.repository.ProductRepository;
import com.cjvision.security_cj.util.IsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductApi {

    @Autowired
    ProductRepository productRepository;

    @GetMapping
   // @RolesAllowed("ROLE_USER")
    @IsUser
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @PostMapping
//    @RolesAllowed({"ROLE_USER, ROLE_EDITOR"})
//    @Secured({"ROLE_USER, ROLE_EDITOR"})
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_EDITOR')")
    public Product addProduct(@RequestBody @Valid Product product){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Auth context " + auth);
        return productRepository.save(product);
    }
}
