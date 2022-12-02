package com.example.rondobackend.repo.order;

import com.example.rondobackend.model.Product;
import com.example.rondobackend.model.order.DailyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DailyOrderRepo extends JpaRepository<DailyOrder, Long> {

}
