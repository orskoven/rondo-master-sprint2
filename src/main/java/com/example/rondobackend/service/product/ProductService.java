package com.example.rondobackend.service.product;

import com.example.rondobackend.model.Product;
import com.example.rondobackend.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService implements IProductService {

    private ProductRepo repo;

    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    @Override
    public Set<Product> findAll() {
        Set<Product> products = new HashSet<>();
        repo.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product save(Product object) {
        repo.save(object);
        return object;
    }

    @Override
    public void delete(Product object) {
        repo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        repo.deleteById(aLong);
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return repo.findById(aLong);
    }


}

