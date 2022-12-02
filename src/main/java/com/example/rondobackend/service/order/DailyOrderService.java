package com.example.rondobackend.service.order;

import com.example.rondobackend.model.order.DailyOrder;
import com.example.rondobackend.repo.order.DailyOrderRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DailyOrderService implements IDailyOrderService{

    private DailyOrderRepo dailyOrderRepo;

    public DailyOrderService(DailyOrderRepo dailyOrderRepo) {
        this.dailyOrderRepo = dailyOrderRepo;
    }

    @Override
    public Set<DailyOrder> findAll() {
        Set<DailyOrder> dailyOrders = new HashSet<>();
        dailyOrderRepo.findAll().forEach(dailyOrders::add);
        return dailyOrders;
    }

    @Override
    public DailyOrder save(DailyOrder object) {
        dailyOrderRepo.save(object);
        return object;
    }

    @Override
    public void delete(DailyOrder object) {
        dailyOrderRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        dailyOrderRepo.deleteById(aLong);
    }

    @Override
    public Optional<DailyOrder> findById(Long aLong) {
        return dailyOrderRepo.findById(aLong);
    }
}
