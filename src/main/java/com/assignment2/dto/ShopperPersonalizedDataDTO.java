package com.assignment2.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopperPersonalizedDataDTO {

    @NotNull(message = "Shopper ID must not be null")
    @NotEmpty(message = "Shopper ID must not be empty")
    private String shopperId;
    private List<ShelfItemDTO> shelf;

}

