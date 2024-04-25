package com.example.tisoproject.service.impl;

import com.example.tisoproject.dto.response.DishResponse;
import com.example.tisoproject.exceptions.NotFoundException;
import com.example.tisoproject.repository.DishRepository;
import com.example.tisoproject.service.DishService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    @Override
    public DishResponse getDishById(Long id) {
        return dishRepository.getDishById(id).orElseThrow(
                ()-> new NotFoundException("There are no any dishes with current ID !!!"));
    }

    @Override
    public List<DishResponse> getAllDishesFromMenu() {
        return dishRepository.getAllDishesFromMenu();
    }

    @Override
    public List<DishResponse> sortedByPrice(String ascOrDesc) {
        return dishRepository.sortDishesByPrice(ascOrDesc);
    }
}