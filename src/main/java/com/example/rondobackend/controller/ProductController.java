package com.example.rondobackend.controller;

import com.example.rondobackend.model.Product;
import com.example.rondobackend.model.ProductDTO;
import com.example.rondobackend.service.product.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@CrossOrigin
@RestController
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/allProducts")
    public ResponseEntity<Set> getProducts(){
        Set<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/createProduct")
    //modelAttribute bruges da multipart/form-data ikke er suported i @RequestBody
    public ResponseEntity<String> createProduct(@ModelAttribute ProductDTO productDTO) throws IOException {
        Product product = new Product();
        product.setPrice(productDTO.getPrice());
        product.setName(productDTO.getName());
        product.setInSortiment(productDTO.isInSortiment());

        product.setPicture(productDTO.getMultipartImage().getBytes());

        productService.save(product);

        return ResponseEntity.ok("nice");
    }



}
