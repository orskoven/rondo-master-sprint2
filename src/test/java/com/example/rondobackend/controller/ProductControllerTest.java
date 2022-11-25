package com.example.rondobackend.controller;

import com.example.rondobackend.model.Product;
import com.example.rondobackend.repo.ProductRepo;
import com.example.rondobackend.service.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest
class ProductControllerTest {
    private MockMvc mockMvc;
    private ProductService productService;

    @Autowired
    ProductRepo productRepo;

    @BeforeEach
    public void setUp(){
        productService = new ProductService(productRepo);
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductController(productService)).build();
    }

    @Test
    void getProducts() throws Exception {
        Product product1 = new Product("brod",20, true);
        Product product2 = new Product("bolle",10, true);

        productService.save(product1);
        productService.save(product2);

    }


    @Test
    void createProduct() throws Exception {
        mockMvc.perform(post("/createProduct")
                        .content("{\"multipartImage\":\"444\",\"name\":\"bolle\",\"price\":\"44\",\"isInSortiment\":\"true\"}"))//Bygger Request boddyen op
                .andExpect(status().isOk())//vi regner med at statusen skal være OK
                .andDo(print());
    }
}
