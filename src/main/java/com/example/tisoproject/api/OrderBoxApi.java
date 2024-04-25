package com.example.tisoproject.api;

import com.example.tisoproject.dto.response.OrderBoxDishResponse;
import com.example.tisoproject.dto.response.OrderBoxResponse;
import com.example.tisoproject.dto.response.SimpleResponse;
import com.example.tisoproject.service.OrderBoxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orderBox")
@PreAuthorize("hasAuthority('User')")
@Tag(name = "OrderBox API")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class OrderBoxApi {

    private final OrderBoxService orderBoxService;

    @PostMapping("/orderDish")
    @Operation(summary = "Сделать заказ блюд", description = "Создает новый заказ для указанных блюд.")
    public OrderBoxResponse orderDishes(@RequestBody List<Long> dishIds) {
        log.info("API для заказа блюд начинает работу...");
        return orderBoxService.orderDish(dishIds);
    }

    @PostMapping("/updateDishes/{orderBoxId}")
    @Operation(summary = "Обновить заказ новыми блюдами", description = "Добавляет новые блюда к существующему заказу.")
    public OrderBoxResponse updateDishes(@PathVariable Long orderBoxId, @RequestBody List<Long> dishIds) {
        log.info("API для заказа блюд обновляет блюда...");
        return orderBoxService.updateDishes(orderBoxId, dishIds);
    }

    @DeleteMapping("/deleteOrder/{orderBoxId}")
    @Operation(summary = "Удалить заказ по ID", description = "Удаляет заказ по его идентификатору.")
    public SimpleResponse deleteOrderBox(@PathVariable Long orderBoxId) {
        log.info("Удаление заказа с идентификатором {}...", orderBoxId);
        return orderBoxService.deleteOrderBox(orderBoxId);
    }

    @PostMapping("/addDish/{orderBoxId}/{dishId}")
    @Operation(summary = "Добавить блюдо по одному в заказ по ID", description = "Добавляет блюдо по одному в заказ по их идентификаторам.")
    public SimpleResponse addDishToOrder(@PathVariable Long orderBoxId, @PathVariable Long dishId) {
        log.info("Добавление блюда с идентификатором {} в заказ с идентификатором {}...", dishId, orderBoxId);
        return orderBoxService.addDishToOrder(orderBoxId, dishId);
    }

    @DeleteMapping("/deleteDish/{orderBoxId}/{dishId}")
    @Operation(summary = "Удалить блюдо из заказа по ID", description = "Удаляет блюдо из заказа по их идентификаторам.")
    public SimpleResponse deleteDishFromOrder(@PathVariable Long orderBoxId, @PathVariable Long dishId) {
        log.info("Удаление блюда с идентификатором {} из заказа с идентификатором {}...", orderBoxId, dishId);
        return orderBoxService.deleteDishFromOrder(orderBoxId, dishId);
    }

    @GetMapping("/orderBoxDishes/{orderBoxId}")
    @Operation(summary = "Получить список заказанных блюд для заказа", description = "Извлекает список заказанных блюд для указанного заказа.")
    public List<OrderBoxDishResponse> getOrderBoxDishes(@PathVariable Long orderBoxId) {
        log.info("Получение списка заказанных блюд для заказа с идентификатором: {}", orderBoxId);
        return orderBoxService.getOrderBoxDishes(orderBoxId);
    }

    @GetMapping("/orderBoxTotalPrice/{orderBoxId}")
    @Operation(summary = "Получить общую стоимость блюд в заказе", description = "Вычисляет общую стоимость всех блюд в указанном заказе.")
    public double getOrderBoxTotalPrice(@PathVariable Long orderBoxId) {
        log.info("Получение общей стоимости всех блюд в заказе с идентификатором {}...", orderBoxId);
        return orderBoxService.getOrderBoxTotalPrice(orderBoxId);
    }
}