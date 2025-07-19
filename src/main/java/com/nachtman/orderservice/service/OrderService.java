package com.nachtman.orderservice.service;

import com.nachtman.orderservice.kafka.OrderProducer;
import com.nachtman.orderservice.model.Item;
import com.nachtman.orderservice.model.Order;
import com.nachtman.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {


    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public OrderService(OrderRepository orderRepository, OrderProducer orderProducer) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
    }

    public Optional<Order> getByOrderId(String order_id) {
        return orderRepository.findById(order_id);
    }

    public Order createOrder(String costumer_name, List<Item> items) {
        var oder = new Order(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                costumer_name,
                "CREATED",
                items,
                Instant.now(),
                Instant.now()
        );
        orderProducer.send(oder);
        return oder;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteById(String order_id) {
        orderRepository.deleteById(order_id);
    }

    public Order updateOrder(String id, String order_status) {
        return orderRepository.findById(id).
                map(existing -> new Order(
                        existing.order_id(),
                        existing.account_id(),
                        existing.costumer_name(),
                        order_status,
                        existing.items(),
                        existing.created_at(),
                        Instant.now()
                ))
                .map(orderRepository::save)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }


}
