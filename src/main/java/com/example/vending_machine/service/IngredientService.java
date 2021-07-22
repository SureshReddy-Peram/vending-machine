package com.example.vending_machine.service;

import com.example.vending_machine.dto.StandardResponse;
import com.example.vending_machine.dto.ingredient.AddIngredientDto;
import com.example.vending_machine.dto.inventory.AddInventoryDto;
import org.springframework.http.ResponseEntity;

public interface IngredientService {
    ResponseEntity<StandardResponse> addIngredient(AddIngredientDto addIngredientDto);
}
