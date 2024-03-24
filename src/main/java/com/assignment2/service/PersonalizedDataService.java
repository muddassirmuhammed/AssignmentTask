package com.assignment2.service;

import com.assignment2.dto.ProductMetadataDTO;
import com.assignment2.dto.ShelfItemDTO;
import com.assignment2.dto.ShopperPersonalizedDataDTO;
import com.assignment2.entity.ProductMetadata;
import com.assignment2.entity.ShelfItem;
import com.assignment2.entity.ShopperPersonalizedData;
import com.assignment2.exception.CustomException;
import com.assignment2.repository.ProductMetadataRepository;
import com.assignment2.repository.ShopperPersonalizedDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalizedDataService {
    @Autowired
    private ShopperPersonalizedDataRepository shopperPersonalizedDataRepository;

    @Autowired
    private ProductMetadataRepository productMetadataRepository;

    public void saveShopperPersonalizedData(ShopperPersonalizedDataDTO data) {
        ShopperPersonalizedData entity = new ShopperPersonalizedData();
        entity.setShopperId(data.getShopperId());
        List<ShelfItem> shelf = data.getShelf().stream()
                .map(this::convertToShelfItemEntity)
                .collect(Collectors.toList());
        entity.setShelf(shelf);
        shopperPersonalizedDataRepository.save(entity);
    }

    public void saveProductMetadata(List<ProductMetadataDTO> dataList) {
        List<ProductMetadata> entities = new ArrayList<>();
        for (ProductMetadataDTO data : dataList) {
            ProductMetadata entity = new ProductMetadata();
            entity.setProductId(data.getProductId());
            entity.setCategory(data.getCategory());
            entity.setBrand(data.getBrand());
            entities.add(entity);
        }
        productMetadataRepository.saveAll(entities);

    }

    public List<ShelfItemDTO> getProducts(String shopperId, String category, String brand, int limit) {
       try {
           List<ShelfItem> shelf = shopperPersonalizedDataRepository.findByShopperId(shopperId).orElseThrow(() ->
                   new IllegalArgumentException("Shopper not found")).getShelf();


           List<ShelfItemDTO> products = shelf.stream()
                   .filter(item -> (category == null || getProductCategory(item.getProductId()).equals(category))
                           && (brand == null || getProductBrand(item.getProductId()).equals(brand)))
                   .sorted((a, b) -> Double.compare(b.getRelevancyScore(), a.getRelevancyScore()))
                   .map(this::convertToShelfItemDTO)
                   .limit(limit)
                   .collect(Collectors.toList());

           return products;
       }
       catch (IllegalArgumentException ex) {
           // Log the exception or perform any other error-handling logic
           ex.printStackTrace();
           throw new CustomException("An error occurred while processing the request", ex);
       }
    }

    private ShelfItem convertToShelfItemEntity(ShelfItemDTO dto) {
        ShelfItem entity = new ShelfItem();
        entity.setProductId(dto.getProductId());
        entity.setRelevancyScore(dto.getRelevancyScore());
        return entity;
    }

    private ShelfItemDTO convertToShelfItemDTO(ShelfItem entity) {
        ShelfItemDTO dto = new ShelfItemDTO();
        dto.setProductId(entity.getProductId());
        dto.setRelevancyScore(entity.getRelevancyScore());
        return dto;
    }

    private String getProductCategory(String productId) {
        return productMetadataRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product metadata not found")).getCategory();
    }

    private String getProductBrand(String productId) {
        return productMetadataRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product metadata not found")).getBrand();
    }
}

