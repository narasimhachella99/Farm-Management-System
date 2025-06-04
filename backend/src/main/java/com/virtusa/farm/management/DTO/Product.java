package com.virtusa.farm.management.DTO;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long quantity;
    private String category;
    private Long price;

    public Product(String name, Long quantity, String category, Long price) {
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
    }
}
