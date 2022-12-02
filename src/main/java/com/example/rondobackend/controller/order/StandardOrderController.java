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
    public ResponseEntity<String> createOrder(@RequestBody Orderl order, @RequestParam Long dailyOrderId){
        Optional<DailyOrder> dailyOrder = dailyOrderService.findById(dailyOrderId);
        if (dailyOrder.isPresent()){
            order.setDailyOrder(dailyOrder.get());
            orderService.save(order);
            return new ResponseEntity<>("order oprettet", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("order ikke oprettet", HttpStatus.OK);
        }
    }
/*
    @PostMapping("updateOrder")
    public ResponseEntity<String> updateOrder(@RequestBody List<Orderl> orders){

        orders.forEach(order -> {
            if (orderService.findById(order.getId()).isPresent() || order.getId() != null){
                Orderl updatetOrder = orderService.findById(order.getId()).get();
                //updatetOrder = order;
                updatetOrder.setAmount(order.getAmount());
                updatetOrder.setProduct(order.getProduct());
                //updatetOrder.setDailyOrder(updatetOrder.getDailyOrder());
                System.out.println(updatetOrder.getDailyOrder());
                orderService.save(updatetOrder);
            }else{
                System.out.println(order.getDailyOrder());
                orderService.save(new Orderl(order.getAmount(), order.getProduct(), dailyOrderService.findById(order.getDailyOrder().getId()).get()));

            }

        });

        return new ResponseEntity<>("ordrere ændrede", HttpStatus.OK);
    }

 */



    @PostMapping("updateOrder")
    public ResponseEntity<String> updateOrder(@RequestBody List<OrderDto> orders){

        orders.forEach(order -> {
            System.out.println(order.getId());
            //Orderl orderl = new Orderl(order.getId(),order.getAmount(),order.getProduct());
            if(order.getProduct() == ""){

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
            }else{
                System.out.println(order.getDailyOrderId());
                orderService.save(new Orderl(order.getAmount(), order.getProduct(), dailyOrderService.findById(order.getDailyOrderId()).get()));
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
