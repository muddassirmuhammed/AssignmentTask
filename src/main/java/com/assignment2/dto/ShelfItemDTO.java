package com.assignment2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShelfItemDTO {

    @NotBlank(message = "Product ID must not be blank")
    private String productId;
    private double relevancyScore;


}
