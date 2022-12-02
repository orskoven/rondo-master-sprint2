package com.example.rondobackend.model.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private Long dailyOrderId;
    private int amount;
    private String product;
}
