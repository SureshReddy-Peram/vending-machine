package com.example.vending_machine.dto.inventory;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddInventoryDto {
    @NotNull
    private String name;

    @NotNull
    private Integer quantity;
}
