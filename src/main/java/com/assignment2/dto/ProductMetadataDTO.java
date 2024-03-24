package com.assignment2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMetadataDTO {

    @NotBlank(message = "Product ID must not be blank")
    private String productId;
    private String category;
    private String brand;




}
