package com.example.rondobackend.service.order;

import com.example.rondobackend.model.order.StandardOrder;
import com.example.rondobackend.service.ICRUDService;

import java.util.Set;

public interface IStandardOrderService extends ICRUDService<StandardOrder, Long> {
    Set<StandardOrder> sortAfterWeekDay();
}
