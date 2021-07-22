package com.example.vending_machine.controller;

import com.example.vending_machine.dto.StandardResponse;
import com.example.vending_machine.dto.beverage.BeverageOrderRequestDto;
import com.example.vending_machine.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(value = "Order Service")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/v1/order/", method = RequestMethod.POST)
    public ResponseEntity<StandardResponse> placeOrder(@Valid @RequestBody BeverageOrderRequestDto payload) {
        return orderService.placeOrder(payload);
    }
}
