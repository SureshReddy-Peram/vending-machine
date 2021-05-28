package com.example.vending_machine.service;

import com.example.vending_machine.dto.StandardResponse;
import com.example.vending_machine.dto.beverage.BeverageOrderRequestDto;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<StandardResponse> placeOrder(BeverageOrderRequestDto payload);

}
