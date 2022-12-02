package com.example.rondobackend.model.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class StandardOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "standardOrder")
    //@JsonIgnore - så bliver alle dailyorders ikke vist når man henter en standardodre
    //@JsonBackReference det samme
    private Set<DailyOrder> dailyOrders = new HashSet<>();


}
