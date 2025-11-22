package com.projectA.dto;

import java.math.BigDecimal;

public record ProductDTO(
        String name,
        BigDecimal price,
        Integer quantity
) {}

