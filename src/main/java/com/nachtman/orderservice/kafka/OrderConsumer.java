package com.nachtman.orderservice.kafka;

import com.nachtman.orderservice.model.Order;
import com.nachtman.orderservice.repository.OrderRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private final OrderRepository orderRepository;

    public OrderConsumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "orders", groupId = "order-group")
    public void listen(Order order) {
        System.out.println("|Kafka|-> Order received from Kafka: " + order.order_id());
        orderRepository.save(order);
    }

}
