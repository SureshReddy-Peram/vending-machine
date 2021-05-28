package com.example.vending_machine.service.impl;

import com.example.vending_machine.dto.StandardResponse;
import com.example.vending_machine.dto.beverage.BeverageOrderRequestDto;
import com.example.vending_machine.model.BeverageEntity;
import com.example.vending_machine.model.IngredientEntity;
import com.example.vending_machine.model.InventoryEntity;
import com.example.vending_machine.repository.BeverageEntityRepository;
import com.example.vending_machine.repository.IngredientEntityRepository;
import com.example.vending_machine.repository.InventoryEntityRepository;
import com.example.vending_machine.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Autowired
    BeverageEntityRepository beverageEntityRepository;

    @Autowired
    InventoryEntityRepository inventoryEntityRepository;

    @Autowired
    IngredientEntityRepository ingredientEntityRepository;

    @Async("asyncOrderExecuter")
    public ResponseEntity<StandardResponse> placeOrder(BeverageOrderRequestDto payload){
        StandardResponse<String> standardResponse = new StandardResponse();
        log.info("placeOrder:: payload {}", payload);
        BeverageEntity beverageEntity = beverageEntityRepository.findByName(payload.getBeverageName());
        if(beverageEntity==null){
            standardResponse.setSuccess(false);
            standardResponse.setMessage("Order can not be fulfilled, Beverage "+payload.getBeverageName()+"not added yet");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardResponse);
        }
        List<IngredientEntity> ingredientEntities = ingredientEntityRepository.findByBeverageId(beverageEntity.getId());
        for(IngredientEntity entity: ingredientEntities){
            // notify to refill inventory
            if(entity.getQuantityRequired() >= entity.getInventory().getQuantity()){
                //notify
                standardResponse.setSuccess(false);
                standardResponse.setMessage("Order can not be fulfilled, ingredient "+entity.getInventory().getName()+" not available");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardResponse);
            }
        }
        // this will update the quantity in inventory
        updateInventory(ingredientEntities);
        standardResponse.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(standardResponse);
    }

    private void updateInventory(List<IngredientEntity> ingredientEntities){
        for(IngredientEntity entity: ingredientEntities){
            InventoryEntity inventoryEntity = entity.getInventory();
            int remainingQuantity = inventoryEntity.getQuantity() - entity.getQuantityRequired();
            inventoryEntity.setQuantity(remainingQuantity);
            inventoryEntity = inventoryEntityRepository.save(inventoryEntity);
        }
    }

}
