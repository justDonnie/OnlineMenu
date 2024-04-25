package com.example.tisoproject.service.impl;

import com.example.tisoproject.dto.response.DishResponse;
import com.example.tisoproject.dto.response.OrderBoxDishResponse;
import com.example.tisoproject.dto.response.OrderBoxResponse;
import com.example.tisoproject.dto.response.SimpleResponse;
import com.example.tisoproject.exceptions.BadCredentialException;
import com.example.tisoproject.exceptions.BadRequestException;
import com.example.tisoproject.exceptions.NotFoundException;
import com.example.tisoproject.models.Dish;
import com.example.tisoproject.models.OrderBox;
import com.example.tisoproject.repository.DishRepository;
import com.example.tisoproject.repository.OrderBoxRepository;
import com.example.tisoproject.service.OrderBoxService;
import jakarta.transaction.Transactional;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderBoxServiceImpl implements OrderBoxService {
    private final OrderBoxRepository orderBoxRepository;
    private final DishRepository dishRepository;

    @Override
    @Transactional
    public OrderBoxResponse orderDish(List<Long> dishIds) {
        log.info("Order Box is creating ...");
        OrderBox orderBox = new OrderBox();
        Map<Long, Integer> dishQuantities = new HashMap<>();
        for (Long dishId : dishIds) {
            dishQuantities.put(dishId, dishQuantities.getOrDefault(dishId, 0) + 1);
        }
        for (Map.Entry<Long, Integer> entry : dishQuantities.entrySet()) {
            Long dishId = entry.getKey();
            Integer quantity = entry.getValue();
            Dish dish = dishRepository.findById(dishId)
                    .orElseThrow(() -> new BadRequestException("Dish with ID " + dishId + " not found"));
            dishRepository.save(dish);
            for (int i = 0; i < quantity; i++) {
                orderBox.addDish(dish);
            }
        }
        orderBoxRepository.save(orderBox);
        log.info("Order Box is successfully created !!!");
        List<DishResponse> dishes;
        if (orderBox.getDishes() != null) {
            dishes = orderBox.getDishes().stream()
                    .map(dish -> DishResponse.builder()
                            .id(dish.getId())
                            .image(dish.getImage())
                            .name(dish.getName())
                            .description(dish.getDescription())
                            .price(dish.getPrice())
                            .build())
                    .collect(Collectors.toList());
        } else {
            log.error("Dishes list is empty !!!");
            throw new BadCredentialException("Dishes list is empty !!!");
        }
        return OrderBoxResponse.builder()
                .orderBoxId(orderBox.getId())
                .orderedDishes(dishes)
                .build();
    }


    @Override
    public OrderBoxResponse updateDishes(Long orderBoxId, List<Long> dishIds) {
        OrderBox orderBox = orderBoxRepository.findById(orderBoxId)
                .orElseThrow(() -> new NotFoundException("Order box not found with id: " + orderBoxId));
        for (Long dishId : dishIds) {
            Dish dish = dishRepository.findById(dishId)
                    .orElseThrow(() -> new NotFoundException("Dish not found with id: " + dishId));
            boolean dishExist = orderBoxRepository.isDishExist(dishId, orderBoxId);
            if (!dishExist) {
                orderBox.addDish(dish);
            }
        }
        orderBoxRepository.save(orderBox);
        List<DishResponse> orderedDishes = orderBox.getDishes().stream()
                .map(dish -> DishResponse.builder()
                        .id(dish.getId())
                        .image(dish.getImage())
                        .name(dish.getName())
                        .description(dish.getDescription())
                        .price(dish.getPrice())
                        .build())
                .collect(Collectors.toList());
        return OrderBoxResponse.builder()
                .orderBoxId(orderBox.getId())
                .orderedDishes(orderedDishes)
                .build();
    }

    @Override
    @Transactional
    public SimpleResponse addDishToOrder(Long orderBoxId, Long dishId) {
        OrderBox orderBox = orderBoxRepository.findById(orderBoxId)
                .orElseThrow(() -> new NotFoundException("Заказ с указанным идентификатором не найден."));
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new NotFoundException("Блюдо с указанным идентификатором не найдено."));
        orderBox.addDish(dish);
        orderBoxRepository.save(orderBox);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully added !!!")
                .build();
    }

    @Override
    @Transactional
    public SimpleResponse deleteDishFromOrder(Long orderBoxId, Long dishId) {
        OrderBox orderBox = orderBoxRepository.findById(orderBoxId)
                .orElseThrow(() -> new NotFoundException("Заказ с указанным идентификатором не найден."));
        boolean dishRemoved = false;
        Iterator<Dish> iterator = orderBox.getDishes().iterator();
        while (iterator.hasNext()) {
            Dish dish = iterator.next();
            if (dish.getId().equals(dishId)) {
                iterator.remove();
                dishRemoved = true;
                orderBox.removeDish(dish);
                break;
            }
        }
        if (dishRemoved) {
            orderBoxRepository.saveAndFlush(orderBox);
            return new SimpleResponse(HttpStatus.OK, "Блюдо из заказа успешно удалено.");
        } else {
            throw new NotFoundException("Блюдо с указанным идентификатором не найдено в заказе.");
        }
    }

    @Override
    @Transactional
    public SimpleResponse deleteOrderBox(Long orderBoxId) {
        orderBoxRepository.deleteByOrderBoxId(orderBoxId);
        return new SimpleResponse(HttpStatus.OK, "Заказ и все блюда из него успешно удалены.");
    }

    @Override
    public List<OrderBoxDishResponse> getOrderBoxDishes(Long orderBoxId) {
        return orderBoxRepository.findOrderedDishesByOrderBoxId(orderBoxId);
    }

    @Override
    public double getOrderBoxTotalPrice(Long orderBoxId) {
        double totalPrice = orderBoxRepository.getAllSumOfDishes(orderBoxId);
        if (totalPrice == 0) {
            throw new NotFoundException("There no any dishes with current ID !!!");
        }
        return totalPrice;
    }
}