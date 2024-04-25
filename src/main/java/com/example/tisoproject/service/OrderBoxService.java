package com.example.tisoproject.service;

import com.example.tisoproject.dto.response.OrderBoxDishResponse;
import com.example.tisoproject.dto.response.OrderBoxResponse;
import com.example.tisoproject.dto.response.SimpleResponse;
import java.util.List;

public interface OrderBoxService {
    OrderBoxResponse orderDish(List<Long> dishIds);
    OrderBoxResponse updateDishes(Long orderBoxId, List<Long> dishIds);
    SimpleResponse deleteOrderBox(Long orderBoxId);
    SimpleResponse addDishToOrder(Long orderBoxId, Long dishId);
    SimpleResponse deleteDishFromOrder(Long orderBoxId, Long dishId);
    List<OrderBoxDishResponse> getOrderBoxDishes(Long orderBoxId);
    double getOrderBoxTotalPrice(Long orderBoxId);
}
