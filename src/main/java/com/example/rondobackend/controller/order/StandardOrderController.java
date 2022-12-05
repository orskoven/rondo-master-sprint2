package com.example.rondobackend.controller.order;

import com.example.rondobackend.model.order.DailyOrder;
import com.example.rondobackend.model.order.OrderDto;
import com.example.rondobackend.model.order.Orderl;
import com.example.rondobackend.model.order.StandardOrder;
import com.example.rondobackend.service.order.IDailyOrderService;
import com.example.rondobackend.service.order.IOrderService;
import com.example.rondobackend.service.order.IStandardOrderService;
import com.example.rondobackend.service.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
public class StandardOrderController {

    private IDailyOrderService dailyOrderService;
    private IOrderService orderService;
    private IStandardOrderService standardOrderService;

    public StandardOrderController(IDailyOrderService dailyOrderService, IOrderService orderService, IStandardOrderService standardOrderService) {
        this.dailyOrderService = dailyOrderService;
        this.orderService = orderService;
        this.standardOrderService = standardOrderService;
    }

    @GetMapping("getStandardOrderById")
            public ResponseEntity<String> getStandardOrderById(@RequestParam Long id){
            return new ResponseEntity(standardOrderService.findById(id).get().getDailyOrders(), HttpStatus.OK);
    }



    @GetMapping("getAllStandardOrders")
    public ResponseEntity<HashSet> getAllStandardOrders(){
        standardOrderService.sortAfterWeekDay();
        return new ResponseEntity(standardOrderService.findAll(), HttpStatus.OK);
    }
    @GetMapping("getAllDailyOrders")
    public ResponseEntity<HashSet> getAllDailyOrders(){
        return new ResponseEntity(dailyOrderService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/createStandardOrder")
    public ResponseEntity<String> createStandardOrder(@RequestBody StandardOrder standardOrder){
        String message = "";
        if (standardOrderService.save(standardOrder) != null){
            message = "oprettet standardOrder " + standardOrder;
        } else {
            message = "standardOrder ikke oprettet " + standardOrder;
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/createDailyOrder")
    public ResponseEntity<String> createDailyOrder(@RequestBody DailyOrder dailyOrder, @RequestParam Long standardOrderId){
        Optional<StandardOrder> standardOrder = standardOrderService.findById(standardOrderId);
        if (standardOrder.isPresent()){
            dailyOrder.setStandardOrder(standardOrder.get());
            dailyOrderService.save(dailyOrder);
            return new ResponseEntity<>("daily-order oprettet", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("daily-order ikke oprettet", HttpStatus.OK);
        }
    }

    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody List<OrderDto> orders){


        for (OrderDto order : orders) {
            Orderl orderl = new Orderl();
            Optional<DailyOrder> dailyOrder = dailyOrderService.findById(order.getDailyOrderId());

            System.out.println("yes" + order.getDailyOrderId());
            if (dailyOrder.isPresent()) {
                orderl.setAmount(order.getAmount());
                orderl.setProduct(order.getProduct());
                orderl.setDailyOrder(dailyOrder.get());
                orderService.save(orderl);
            }
        }
        return new ResponseEntity<>("order oprettet", HttpStatus.OK);
    }

    @PostMapping("/deleteOrder")
    public ResponseEntity<String> deleteOrder(@RequestBody List<OrderDto> orders){

        for (OrderDto order : orders) {
            if (orderService.findById(order.getId()).isPresent()) {
                orderService.deleteById(order.getId());
            }
        }
        return new ResponseEntity<>("order slettet", HttpStatus.OK);
    }

    @PostMapping("updateOrder")
    public ResponseEntity<String> updateOrder(@RequestBody List<OrderDto> orders){

        orders.forEach(order -> {
            //Orderl orderl = new Orderl(order.getId(),order.getAmount(),order.getProduct());
            if(order.getProduct() == ""){
                System.out.println(order.getId());
                orderService.deleteById(order.getId());
            }else if (order.getId() != null) {
                Orderl orderl = new Orderl(order.getId(), order.getAmount(), order.getProduct());
                Orderl updatetOrder = orderService.findById(orderl.getId()).get();
                //updatetOrder = order;
                updatetOrder.setAmount(order.getAmount());
                updatetOrder.setProduct(order.getProduct());
                //updatetOrder.setDailyOrder(updatetOrder.getDailyOrder());
                System.out.println(updatetOrder.getDailyOrder());
                orderService.save(updatetOrder);
            }
        });

        return new ResponseEntity<>("ordrere ændrede", HttpStatus.OK);
    }



    @PostMapping("updateStandardOrder")
    public ResponseEntity<String> updateStandardOrder(@RequestBody StandardOrder standardOrder){
        StandardOrder updatetOrder = standardOrderService.findById(standardOrder.getId()).get();
        updatetOrder = standardOrder;
        standardOrderService.save(updatetOrder);
        return new ResponseEntity<>(standardOrder + " ændret til: " + updatetOrder, HttpStatus.OK);
    }
}
