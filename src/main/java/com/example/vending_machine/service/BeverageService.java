package com.example.vending_machine.service;

import com.example.vending_machine.dto.StandardResponse;
import com.example.vending_machine.dto.beverage.AddBeverageDto;
import com.example.vending_machine.dto.beverage.BeverageOrderRequestDto;
import org.springframework.http.ResponseEntity;

public interface BeverageService {
    ResponseEntity<StandardResponse> addBeverage(AddBeverageDto addBeverageDto);

    ResponseEntity<StandardResponse> getBeverages();

}
