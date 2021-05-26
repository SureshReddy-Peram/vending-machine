package com.example.vending_machine.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ingredient_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientEntity {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "ingredient_id_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="beverage_details")
    @ToString.Exclude
    private BeverageEntity beverage;

    @ManyToOne
    @JoinColumn(name="inventory_details")
    private InventoryEntity inventory;

    private Integer quantityRequired;
}
