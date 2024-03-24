package com.assignment2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ShopperPersonalizedData {

    @Id
    @NotNull(message = "Shopper ID must not be null")
    @NotEmpty(message = "Shopper ID must not be empty")
    private String shopperId;

    @ElementCollection
    private List<ShelfItem> shelf;


}


