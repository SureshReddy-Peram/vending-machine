package com.example.vending_machine.dto.ingredient;

import com.example.vending_machine.dto.inventory.AddInventoryDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddIngredientDto {
    @Expose
    @SerializedName("inventory")
    private AddInventoryDto inventory;

    @Expose
    @SerializedName("quantity_required")
    private Integer quantityRequired;
}
