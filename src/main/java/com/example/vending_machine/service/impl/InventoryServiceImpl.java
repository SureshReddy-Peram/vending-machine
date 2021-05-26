package com.example.vending_machine.service.impl;

import com.example.vending_machine.dto.StandardResponse;
import com.example.vending_machine.dto.inventory.AddInventoryDto;
import com.example.vending_machine.model.InventoryEntity;
import com.example.vending_machine.repository.InventoryEntityRepository;
import com.example.vending_machine.service.InventoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    InventoryEntityRepository inventoryEntityRepository;

    public ResponseEntity<StandardResponse> addInventory(AddInventoryDto payload){
        StandardResponse<String> standardResponse = new StandardResponse();
        log.info("addInventory:: payload - {}", payload);
        InventoryEntity inventoryEntity = InventoryEntity.builder()
                .name(payload.getName())
                .quantity(payload.getQuantity())
                .build();
        inventoryEntity = inventoryEntityRepository.save(inventoryEntity);
        standardResponse.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(standardResponse);
    }
}
