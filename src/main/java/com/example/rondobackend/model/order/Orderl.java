package com.example.rondobackend.model.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Orderl {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private int amount;
    private String product;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private DailyOrder dailyOrder;

    public Orderl() {
    }

    public Orderl(int amount, String product, DailyOrder dailyOrder) {
        this.amount = amount;
        this.product = product;
        this.dailyOrder = dailyOrder;
    }

    public Orderl(Long id, int amount, String product) {
        this.id = id;
        this.amount = amount;
        this.product = product;
    }
}
