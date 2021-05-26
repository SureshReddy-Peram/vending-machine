package com.example.vending_machine.dto.beverage;

import com.example.vending_machine.pojo.Beverage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeverageResponseDto {
    private List<Beverage> beverageList;
}
