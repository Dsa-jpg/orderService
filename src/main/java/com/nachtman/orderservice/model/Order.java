package com.nachtman.orderservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "orders")
public record Order(@Id String order_id, String account_id, String costumer_name, String order_status, List<Item> items,
                    Instant created_at, Instant updated_at) {
}
