package com.example.vending_machine.repository;

import com.example.vending_machine.model.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryEntityRepository extends JpaRepository<InventoryEntity, Long> {
    InventoryEntity findByName(String name);
}
