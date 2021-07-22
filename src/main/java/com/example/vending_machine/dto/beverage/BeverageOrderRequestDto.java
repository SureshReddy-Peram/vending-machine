package com.example.vending_machine.dto.beverage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeverageOrderRequestDto {
    @Expose
    @SerializedName("beverage_name")
    private String beverageName;

//    @Expose
//    @SerializedName("quantity")
//    private String quantity;
}
