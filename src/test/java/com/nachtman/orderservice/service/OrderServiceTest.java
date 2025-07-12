package com.nachtman.orderservice.service;

import com.nachtman.orderservice.model.Item;
import com.nachtman.orderservice.model.Order;
import com.nachtman.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class OrderServiceTest {


    private OrderService orderService;
    private OrderRepository orderRepository;


    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }

    @Test
    void createOrder() {
        var items = List.of(new Item(UUID.randomUUID(), "PC", 12.5),
                new Item(UUID.randomUUID(), "Phone", 12.5));
        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);

        when(orderRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Order order = orderService.createOrder("Filip Nachtman", items);

        verify(orderRepository).save(orderArgumentCaptor.capture());

        Order capturedOrder = orderArgumentCaptor.getValue();

        assertThat(order.costumer_name()).isEqualTo("Filip Nachtman");
        assertThat(order.order_status()).isEqualTo("CREATED");
        assertThat(order.created_at()).isNotNull();
        assertThat(order).isEqualTo(capturedOrder);


    }

    @Test
    void findById() {
    }

    @Test
    void getAllOrders() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void updateOrder() {
    }
}