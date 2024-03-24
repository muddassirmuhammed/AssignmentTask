package com.assignment2.repository;

import com.assignment2.entity.ProductMetadata;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductMetadataRepository extends JpaRepository<ProductMetadata, String> {
    Optional<ProductMetadata> findByProductId(String productId);

}
