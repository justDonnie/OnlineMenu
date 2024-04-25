package com.example.tisoproject.dto.response;

import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderBoxResponse {
    private Long orderBoxId;
    private List<DishResponse> orderedDishes;
}
