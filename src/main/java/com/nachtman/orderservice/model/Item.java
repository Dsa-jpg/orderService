package com.nachtman.orderservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items")
public record Item(@Id String item_id, String description, Double price) {
}
