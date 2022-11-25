package com.example.rondobackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String Name;
    private int price;
    private boolean isInSortiment;

    public Product(String name, int price, boolean isInSortiment) {
        Name = name;
        this.price = price;
        this.isInSortiment = isInSortiment;
    }

    @Lob
    private byte[] picture;

}
