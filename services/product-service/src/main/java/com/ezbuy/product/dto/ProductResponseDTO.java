package com.ezbuy.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(
    UUID publicId,
    String name,
    BigDecimal price,
    Integer stock,
    String description
) {}


