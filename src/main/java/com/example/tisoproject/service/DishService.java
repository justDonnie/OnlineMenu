package com.example.tisoproject.service;

import com.example.tisoproject.dto.response.DishResponse;
import java.util.List;

public interface DishService {
    DishResponse getDishById(Long id);
    List<DishResponse> getAllDishesFromMenu();
    List<DishResponse>sortedByPrice(String ascOrDesc);
}
