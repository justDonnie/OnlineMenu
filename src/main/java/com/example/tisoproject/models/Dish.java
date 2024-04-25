package com.example.tisoproject.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dishes")
@NoArgsConstructor
@Getter
@Setter
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dish_gen")
    @SequenceGenerator(name = "dish_gen", sequenceName = "dish_seq", allocationSize = 1, initialValue = 11)
    private Long id;
    private String image;
    private String name;
    private String description;
    private BigDecimal price;

    @ManyToMany
    @JoinTable(
            name = "dish_order_box",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "order_box_id")
    )
    private List<OrderBox> orderBox;

    public Dish(String image, String name, String description,
                BigDecimal price, List<OrderBox> orderBox) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.orderBox = orderBox;
    }

    public Dish(Long id, String image, String name, String description, BigDecimal price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}