package com.example.vending_machine.controller;

import com.example.vending_machine.dto.StandardResponse;
import com.example.vending_machine.dto.beverage.AddBeverageDto;
import com.example.vending_machine.dto.inventory.AddInventoryDto;
import com.example.vending_machine.service.BeverageService;
import com.example.vending_machine.service.InventoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@Api(value = "Inventory Service")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @RequestMapping(value = "/v1/inventory/", method = RequestMethod.POST)
    public ResponseEntity<StandardResponse> addInventory(@Valid @RequestBody AddInventoryDto payload) {
        return inventoryService.addInventory(payload);
    }
}
