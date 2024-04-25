package com.example.tisoproject.dto.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderBoxDishResponse {
    private String name;
    private BigDecimal price;
    private Long countOfDishes;

    public OrderBoxDishResponse(String name, BigDecimal price, Long countOfDishes) {
        this.name = name;
        this.price = price;
        this.countOfDishes = countOfDishes;
    }
}