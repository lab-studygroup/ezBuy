package com.ezbuy.product.dto;

import java.math.BigDecimal;

public record ProductRequestDTO(
    String name,
    BigDecimal price,
    Integer stock,
    String description
) {}


