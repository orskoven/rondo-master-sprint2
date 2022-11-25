package com.example.rondobackend.service.product;

import com.example.rondobackend.model.Product;
import com.example.rondobackend.repo.ProductRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ProductServiceTest {

    private ProductService productService;

    @Autowired
    private ProductRepo productRepo;

    @BeforeEach//@BeforeEach betyder at denne metode bliver kørt før hver @Test
    public void setUp(){
        productService = new ProductService(productRepo);
    }


    @Test
    void findAll() {
        for (int i = 0; i < 10; i++) {
            Product product = new Product("brød",i, true);
            productService.save(product);
        }
        Assertions.assertEquals(10, productService.findAll().size()); //tjekker om størrelsen er 10 - der er altså 10 objecter i listen, efter der er tilføjet 10
    }

    @Test
    void save() {
        Product product = new Product("brød",12, true);
        Product savedProduct = productService.save(product);
        Assertions.assertNotNull(savedProduct); //tjekker den er gamt
        Assertions.assertEquals("brød", savedProduct.getName()); //tjekker om usernamet korrekt
        Assertions.assertEquals(12, savedProduct.getPrice());
        Assertions.assertTrue(savedProduct.isInSortiment());
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findById() {
    }
}