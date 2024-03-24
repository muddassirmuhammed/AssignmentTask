package com.assignment2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ShelfItem {

    @NotBlank(message = "Product ID must not be blank")
    private String productId;
    private double relevancyScore;


}
