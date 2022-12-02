package com.example.rondobackend.repo.order;

import com.example.rondobackend.model.order.DailyOrder;
import com.example.rondobackend.model.order.Orderl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderRepo extends JpaRepository<Orderl, Long> {
    Set<Orderl> findAllByDailyOrder(DailyOrder dailyOrder);
}
