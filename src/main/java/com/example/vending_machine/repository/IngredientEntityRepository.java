package com.example.vending_machine.repository;

import com.example.vending_machine.model.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientEntityRepository extends JpaRepository<IngredientEntity, Long> {
    IngredientEntity findById(String name);
    List<IngredientEntity> findByBeverageId(Long Id);
}
