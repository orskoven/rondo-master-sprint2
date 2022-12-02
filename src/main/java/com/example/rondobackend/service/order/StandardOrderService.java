package com.example.rondobackend.service.order;

import com.example.rondobackend.model.order.DailyOrder;
import com.example.rondobackend.model.order.StandardOrder;
import com.example.rondobackend.repo.order.StandardOrderRepo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StandardOrderService implements IStandardOrderService{
    private StandardOrderRepo standardOrderRepo;

    public StandardOrderService(StandardOrderRepo standardOrderRepo) {
        this.standardOrderRepo = standardOrderRepo;
    }

    @Override
    public Set<StandardOrder> findAll() {
        Set<StandardOrder> standardOrders = new HashSet<>();
        standardOrderRepo.findAll().forEach(standardOrders::add);
        return standardOrders;
    }

    @Override
    public StandardOrder save(StandardOrder object) {
        standardOrderRepo.save(object);
        return object;
    }

    @Override
    public void delete(StandardOrder object) {
        standardOrderRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        standardOrderRepo.deleteById(aLong);
    }

    @Override
    public Optional<StandardOrder> findById(Long aLong) {
        return standardOrderRepo.findById(aLong);
    }

    @Override
    public Set<StandardOrder> sortAfterWeekDay(){
        Set<StandardOrder> standardOrders = findAll();
        Set<DailyOrder> sortedDailyOrders= new HashSet<>();

        for(StandardOrder standardOrder : standardOrders){
            sortedDailyOrders = standardOrder.getDailyOrders();



        }
        return standardOrders;
    }
}

