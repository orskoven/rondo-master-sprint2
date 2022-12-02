package com.example.rondobackend.model.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class DailyOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String weekday;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private StandardOrder standardOrder;

    @OneToMany(mappedBy = "dailyOrder")
    //@JsonIgnore - så bliver orders ikke vist når man henter en dailyorder
    //@JsonBackReference det samme
    private Set<Orderl> orders = new HashSet<>();


}
