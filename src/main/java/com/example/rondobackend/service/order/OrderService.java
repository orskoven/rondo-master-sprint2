package com.example.rondobackend.service.order;

import com.example.rondobackend.model.order.DailyOrder;
import com.example.rondobackend.model.order.Orderl;
import com.example.rondobackend.repo.order.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService implements IOrderService{

    private OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public Set<Orderl> findAll() {
        Set<Orderl> orders = new HashSet<>();
        orderRepo.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Orderl save(Orderl object) {
        orderRepo.save(object);
        return object;
    }

    @Override
    public void delete(Orderl object) {
        orderRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        orderRepo.deleteById(aLong);

    }

    @Override
    public Optional<Orderl> findById(Long aLong) {
        return orderRepo.findById(aLong);
    }

    @Override
    public Set<Orderl> findAllByDailyOrder(DailyOrder dailyOrder) {
        Set<Orderl> orders = new HashSet<>();
        orderRepo.findAllByDailyOrder(dailyOrder).forEach(orders::add);
        return orders;
    }
}
