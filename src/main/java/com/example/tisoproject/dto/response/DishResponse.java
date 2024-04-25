package com.example.tisoproject.dto.response;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class DishResponse {
    private Long id;
    private String image;
    private String name;
    private String description;
    private BigDecimal price;

    public DishResponse(Long id, String image, String name,
                        String description,BigDecimal price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.price=price;
    }
}