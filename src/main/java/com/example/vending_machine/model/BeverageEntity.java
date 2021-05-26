package com.example.vending_machine.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "beverage_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeverageEntity {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "beverage_id_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany
    @JsonManagedReference
    @JoinColumn(name="ingredient_details")
    private List<IngredientEntity> ingredients;
}
