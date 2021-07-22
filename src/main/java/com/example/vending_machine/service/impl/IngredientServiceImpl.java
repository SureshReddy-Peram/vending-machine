package com.example.vending_machine.service.impl;

import com.example.vending_machine.dto.StandardResponse;
import com.example.vending_machine.dto.ingredient.AddIngredientDto;
import com.example.vending_machine.repository.IngredientEntityRepository;
import com.example.vending_machine.service.IngredientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    IngredientEntityRepository ingredientEntityRepository;

    public ResponseEntity<StandardResponse> addIngredient(AddIngredientDto payload){
        StandardResponse<String> standardResponse = new StandardResponse();
        log.info("addIngredient:: payload - {}", payload);
        return ResponseEntity.status(HttpStatus.OK).body(standardResponse);
    }
}
