package com.example.rondobackend.service.product;

import com.example.rondobackend.model.Product;
import com.example.rondobackend.service.ICRUDService;

import java.util.Set;

public interface IProductService extends ICRUDService<Product, Long> {
    Set<Product> findAllByisInSortimentTrue();
}
