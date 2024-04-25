package com.example.tisoproject.dto.request;

import lombok.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DishRequest {
    private String image;
    private String name;
    private String description;
    private BigDecimal price;

}
