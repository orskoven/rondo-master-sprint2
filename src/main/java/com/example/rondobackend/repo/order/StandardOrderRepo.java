package com.example.rondobackend.repo.order;

import com.example.rondobackend.model.Product;
import com.example.rondobackend.model.order.StandardOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface StandardOrderRepo extends JpaRepository<StandardOrder, Long> {

}
