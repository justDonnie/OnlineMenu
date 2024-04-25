package com.example.tisoproject.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_boxes")
@NoArgsConstructor
@Getter
@Setter
public class OrderBox {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_box_gen")
    @SequenceGenerator(name = "order_box_gen", sequenceName = "order_box_seq", allocationSize = 1, initialValue = 5)
    private Long id;

    @ManyToMany(mappedBy = "orderBox", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Dish> dishes;

    public void addDish(Dish dish) {
        if (dishes == null) {
            dishes = new ArrayList<>();
        }
        dishes.add(dish);
        dish.getOrderBox().add(this);
    }

    public void removeDish(Dish dish) {
        if (dishes != null) {
            dishes.remove(dish);
            dish.getOrderBox().remove(this);
        }
    }
}