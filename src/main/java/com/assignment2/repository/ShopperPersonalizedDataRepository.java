package com.assignment2.repository;

import com.assignment2.entity.ShopperPersonalizedData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopperPersonalizedDataRepository extends JpaRepository<ShopperPersonalizedData, String> {
    Optional<ShopperPersonalizedData> findByShopperId(String shopperId);
}
