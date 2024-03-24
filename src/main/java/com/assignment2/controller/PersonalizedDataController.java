package com.assignment2.controller;

import com.assignment2.dto.ProductMetadataDTO;
import com.assignment2.dto.ShelfItemDTO;
import com.assignment2.dto.ShopperPersonalizedDataDTO;
import com.assignment2.service.PersonalizedDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonalizedDataController {
    @Autowired
    private PersonalizedDataService personalizedDataService;

    @PostMapping("/internal/receive_personalized_data")
    public ResponseEntity<?> receivePersonalizedData(@Valid @RequestBody ShopperPersonalizedDataDTO data) {
        personalizedDataService.saveShopperPersonalizedData(data);
        return ResponseEntity.ok().body("Personalized data received successfully");
    }

    @PostMapping("/internal/receive_product_metadata")
    public ResponseEntity<?> receiveProductMetadata(@Valid @RequestBody List<ProductMetadataDTO> dataList) {

        personalizedDataService.saveProductMetadata(dataList);

        return ResponseEntity.ok().body("Product metadata received successfully");
    }

    @GetMapping("/external/get_products")
    public ResponseEntity<?> getProducts(
            @RequestParam String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(defaultValue = "10") int limit
    ) {
        List<ShelfItemDTO> products = personalizedDataService.getProducts(shopperId, category, brand, limit);
        return ResponseEntity.ok().body(products);
    }
}

