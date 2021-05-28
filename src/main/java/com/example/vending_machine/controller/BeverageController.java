package com.example.vending_machine.controller;

import com.example.vending_machine.dto.StandardResponse;
import com.example.vending_machine.dto.beverage.AddBeverageDto;
import com.example.vending_machine.dto.beverage.BeverageOrderRequestDto;
import com.example.vending_machine.service.BeverageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "Beverage Service")
public class BeverageController {
    @Autowired
    BeverageService beverageService;

    @RequestMapping(value = "/v1/beverages/", method = RequestMethod.POST)
    public ResponseEntity<StandardResponse> addBeverage(@Valid @RequestBody AddBeverageDto payload) {
        return beverageService.addBeverage(payload);
    }

    @RequestMapping(value = "/v1/beverages/", method = RequestMethod.GET)
    public ResponseEntity<StandardResponse> getBeverages() {
        return beverageService.getBeverages();
    }

    @RequestMapping(value = "/v1/order/", method = RequestMethod.POST)
    public ResponseEntity<StandardResponse> placeOrder(@Valid @RequestBody BeverageOrderRequestDto payload) {
        return beverageService.placeOrder(payload);
    }
}
