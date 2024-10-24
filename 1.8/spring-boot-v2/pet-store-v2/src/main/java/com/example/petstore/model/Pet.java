package com.example.petstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Document(collection = "pet")
public class Pet {
    private @Id String id;
    private Long cost;
    private Integer numberInStock;
    private String itemName;
    private String pictureUrl;
}
