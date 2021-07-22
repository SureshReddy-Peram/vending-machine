package com.example.vending_machine.service;

import com.example.vending_machine.dto.StandardResponse;
import com.example.vending_machine.dto.inventory.AddInventoryDto;
import org.springframework.http.ResponseEntity;


public interface InventoryService {
    ResponseEntity<StandardResponse> addInventory(AddInventoryDto addInventoryDto);
}
