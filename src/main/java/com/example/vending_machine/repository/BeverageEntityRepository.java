package com.example.vending_machine.repository;

import com.example.vending_machine.model.BeverageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeverageEntityRepository extends JpaRepository<BeverageEntity, Long> {
    BeverageEntity findByName(String name);
    List<BeverageEntity> findAll();
}
