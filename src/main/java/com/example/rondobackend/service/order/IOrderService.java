package com.example.rondobackend.service.order;

import com.example.rondobackend.model.order.DailyOrder;
import com.example.rondobackend.model.order.Orderl;
import com.example.rondobackend.service.ICRUDService;

import java.util.Set;

public interface IOrderService extends ICRUDService<Orderl, Long> {
    Set<Orderl> findAllByDailyOrder(DailyOrder dailyOrder);
}
