package com.ezbuy.product.mapper;

import com.ezbuy.product.dto.ProductRequestDTO;
import com.ezbuy.product.dto.ProductResponseDTO;
import com.ezbuy.product.model.Product;

public class ProductMapper {
    public static Product toEntity(ProductRequestDTO dto) {
        return Product.builder()
                .name(dto.name())
                .price(dto.price())
                .stock(dto.stock())
                .description(dto.description())
                .build();
    }

    public static ProductResponseDTO toResponse(Product product) {
        return new ProductResponseDTO(
                product.getPublicId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getDescription()
        );
    }
}
