package com.example.tisoproject.api;

import com.example.tisoproject.dto.response.DishResponse;
import com.example.tisoproject.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('User')")
@RequestMapping("/api/auth")
@Tag(name = "Dish API")
public class DishApi {

    private final DishService dishService;

    @GetMapping("/getDishById")
    @Operation(description = "Получение блюд по идентификатору ")
    public DishResponse getDishByID(@RequestParam Long dishId) {
        return dishService.getDishById(dishId);
    }


    @GetMapping("/getAllDishes")
    @Operation(description = "Получение всех блюд")
    public List<DishResponse> getAllDishes() {
        return dishService.getAllDishesFromMenu();
    }

    @GetMapping("/getSortedDishes")
    @Operation(description = "Получение сортированных блюд по цене")
    public List<DishResponse> getSortedDishesByPrice(@RequestParam String ascOrDesc) {
        return dishService.sortedByPrice(ascOrDesc);
    }
}
