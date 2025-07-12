package com.nachtman.orderservice.controller;

import com.nachtman.orderservice.model.CreateOrderRequest;
import com.nachtman.orderservice.model.Order;
import com.nachtman.orderservice.model.UpdateOrderRequest;
import com.nachtman.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<Order> getAll() {
        return service.getAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Order> getById(@PathVariable String id) {
        return service.getByOrderId(id);
    }

    @PostMapping
    public Order create(@RequestBody CreateOrderRequest order) {
        return service.createOrder(order.costumer_name(), order.items());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteById(id);
    }

    @PatchMapping("/{id}/status")
    public Order update(@PathVariable String id, @RequestBody UpdateOrderRequest order) {
        return service.updateOrder(id, order.status());
    }
}
