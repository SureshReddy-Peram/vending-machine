package com.example.vending_machine.service.impl;

import com.example.vending_machine.dto.StandardResponse;
import com.example.vending_machine.dto.beverage.AddBeverageDto;
import com.example.vending_machine.dto.beverage.BeverageOrderRequestDto;
import com.example.vending_machine.dto.beverage.BeverageResponseDto;
import com.example.vending_machine.dto.ingredient.AddIngredientDto;
import com.example.vending_machine.dto.inventory.AddInventoryDto;
import com.example.vending_machine.model.BeverageEntity;
import com.example.vending_machine.model.IngredientEntity;
import com.example.vending_machine.model.InventoryEntity;
import com.example.vending_machine.pojo.Beverage;
import com.example.vending_machine.pojo.Ingredient;
import com.example.vending_machine.repository.BeverageEntityRepository;
import com.example.vending_machine.repository.IngredientEntityRepository;
import com.example.vending_machine.repository.InventoryEntityRepository;
import com.example.vending_machine.service.BeverageService;
import com.example.vending_machine.utils.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class BeverageServiceImpl implements BeverageService {
    @Autowired
    BeverageEntityRepository beverageEntityRepository;

    @Autowired
    InventoryEntityRepository inventoryEntityRepository;

    @Autowired
    IngredientEntityRepository ingredientEntityRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();


    public ResponseEntity<StandardResponse> addBeverage(AddBeverageDto addBeverageDto){
        StandardResponse<String> standardResponse = new StandardResponse();
        log.info("addBeverage:: payload - {}", addBeverageDto);
        try{
            BeverageEntity beverageEntity = beverageEntityRepository.findByName(addBeverageDto.getName());
            if(beverageEntity==null){
                beverageEntity = BeverageEntity.builder()
                        .name(addBeverageDto.getName())
                        .build();
                beverageEntityRepository.save(beverageEntity);
            }
            List<IngredientEntity> ingredientEntities = new ArrayList<>();
            for (Ingredient ingredient: addBeverageDto.getIngredients()){
                InventoryEntity inventoryEntity = inventoryEntityRepository.findByName(ingredient.getName());
                if(inventoryEntity!=null){
                        IngredientEntity ingredientEntity = IngredientEntity.builder()
                                .beverage(beverageEntity)
                                .inventory(inventoryEntity)
                                .quantityRequired(ingredient.getQuantityRequired())
                                .build();
                        ingredientEntity = ingredientEntityRepository.save(ingredientEntity);
                        ingredientEntities.add(ingredientEntity);
                } else {
                    standardResponse.setMessage("Please add ingredient "+ ingredient.getName());
                    standardResponse.setSuccess(false);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardResponse);
                }
            }
            beverageEntity.setIngredients(ingredientEntities);
            beverageEntityRepository.save(beverageEntity);
        } catch (Exception e){
            log.error("addBeverage:: Exception "+e.getMessage());
        }
        standardResponse.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(standardResponse);
    }

    public ResponseEntity<StandardResponse> getBeverages(){
        StandardResponse<List<String>> standardResponse = new StandardResponse();
        log.info("getBeverages:: called ");
        List<BeverageEntity> beverageEntities = beverageEntityRepository.findAll();
        List<String> beverageList = new ArrayList<>();
        for(BeverageEntity beverageEntity: beverageEntities)
            beverageList.add(beverageEntity.getName());
        standardResponse.setSuccess(true);
        standardResponse.setData(beverageList);
        return ResponseEntity.status(HttpStatus.OK).body(standardResponse);
    }

    public ResponseEntity<StandardResponse> placeOrder(BeverageOrderRequestDto payload){
        StandardResponse<String> standardResponse = new StandardResponse();
        log.info("placeOrder:: payload {}", payload);
        BeverageEntity beverageEntity = beverageEntityRepository.findByName(payload.getBeverageName());
        List<IngredientEntity> ingredientEntities = ingredientEntityRepository.findByBeverageId(beverageEntity.getId());
        for(IngredientEntity entity: ingredientEntities){
            // notify to refill inventory
            if(entity.getQuantityRequired() >= entity.getInventory().getQuantity()){
                //notify
                standardResponse.setSuccess(false);
                standardResponse.setMessage("Order can not fulfilled, ingredient {} not available");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardResponse);
            }
        }
        // this will update the quantity in inventory
        processOrder(ingredientEntities);
        return ResponseEntity.status(HttpStatus.OK).body(standardResponse);
    }

    private void processOrder(List<IngredientEntity> ingredientEntities){
        for(IngredientEntity entity: ingredientEntities){
            InventoryEntity inventoryEntity = entity.getInventory();
            int remainingQuantity = inventoryEntity.getQuantity() - entity.getQuantityRequired();
            inventoryEntity.setQuantity(remainingQuantity);
            inventoryEntity = inventoryEntityRepository.save(inventoryEntity);
        }
    }
}
