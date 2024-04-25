package com.example.tisoproject.repository;

import com.example.tisoproject.dto.response.DishResponse;
import com.example.tisoproject.models.Dish;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    @Query(value = "select new com.example.tisoproject.dto.response.DishResponse(d.id,d.image,d.name,d.description,d.price)" +
                   " from Dish d where d.id=:dishId")
    Optional<DishResponse> getDishById(Long dishId);

    @Query(value = "select new com.example.tisoproject.models.Dish(d.id,d.image,d.name,d.description,d.price)" +
                   " from Dish d where d.id=:dishId ")
    Optional<Dish> getDishesById(Long dishId);

    @Query("select new com.example.tisoproject.dto.response.DishResponse(d.id,d.image,d.name,d.description,d.price)" +
           " from Dish d ")
    List<DishResponse> getAllDishesFromMenu();

    @Query("select new com.example.tisoproject.models.Dish( d.id,d.image,d.name,d.description,d.price ) " +
           "from Dish d where d.id=:dishId ")
    List<Dish>findAllDishes(Long dishId);

    @Query("SELECT new com.example.tisoproject.dto.response.DishResponse( d.id, d.image, d.name, d.description, d.price) " +
           "FROM Dish d "+
           "GROUP BY d.id, d.image, d.name, d.description, d.price " +
           "ORDER BY CASE WHEN :ascOrDesc = 'asc' THEN d.price END ASC, " +
           "         CASE WHEN :ascOrDesc = 'desc' THEN d.price END DESC ")
    List<DishResponse> sortDishesByPrice(String ascOrDesc);
}