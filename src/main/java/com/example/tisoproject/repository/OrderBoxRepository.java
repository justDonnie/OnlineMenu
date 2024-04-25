package com.example.tisoproject.repository;

import com.example.tisoproject.dto.response.OrderBoxDishResponse;
import com.example.tisoproject.models.OrderBox;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBoxRepository extends JpaRepository<OrderBox, Long> {

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM OrderBox o JOIN o.dishes d WHERE o.id = :orderBoxId AND d.id = :dishId")
    boolean isDishExist(@Param("dishId") Long dishId, @Param("orderBoxId") Long orderBoxId);

    @Query("SELECT new com.example.tisoproject.dto.response.OrderBoxDishResponse(d.name, d.price, COUNT(d.id)) " +
           "FROM Dish d JOIN d.orderBox o " +
           "WHERE o.id = :orderBoxId " +
           "GROUP BY d.name, d.price ")
    List<OrderBoxDishResponse> findOrderedDishesByOrderBoxId(@Param("orderBoxId") Long orderBoxId);

    @Query("select sum(d.price) from Dish d join d.orderBox o where o.id =:orderBoxId ")
    double getAllSumOfDishes(Long orderBoxId);

    @Modifying
    @Query(value = "DELETE FROM dish_order_box WHERE order_box_id = :orderBoxId", nativeQuery = true)
    void deleteByOrderBoxId(Long orderBoxId);
}