package com.nachtman.orderservice.model;

import java.time.Instant;
import java.util.List;

public record Order(String order_id, String account_id, String costumer_name, String order_status, List<Item> items,
                    Instant created_at, Instant updated_at) {
}
