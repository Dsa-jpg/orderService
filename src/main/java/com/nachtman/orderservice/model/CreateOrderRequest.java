package com.nachtman.orderservice.model;

import java.util.List;

public record CreateOrderRequest(String costumer_name, List<Item> items) {
}
