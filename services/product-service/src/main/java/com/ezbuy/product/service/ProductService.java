package com.ezbuy.product.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ezbuy.product.model.Product;
import com.ezbuy.product.repository.ProductRepository;
import com.ezbuy.product.dto.ProductRequestDTO;
import com.ezbuy.product.dto.ProductResponseDTO;
import com.ezbuy.product.mapper.ProductMapper;
import com.ezbuy.product.exception.ProductNotFoundException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO findByPublicId(UUID publicId) {
        return productRepository.findByPublicId(publicId)
                .map(ProductMapper::toResponse)
                .orElseThrow(() -> new ProductNotFoundException("Product not found: " + publicId));
    }

    public ProductResponseDTO create(ProductRequestDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        return ProductMapper.toResponse(productRepository.save(product));
    }

    public ProductResponseDTO update(UUID publicId, ProductRequestDTO dto) {
        Product existing = productRepository.findByPublicId(publicId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found: " + publicId));

        Product updated = ProductMapper.toEntity(dto);
        updated.setId(existing.getId());
        updated.setPublicId(existing.getPublicId());

        return ProductMapper.toResponse(productRepository.save(updated));
    }

    public void delete(UUID publicId) {
        try {
            productRepository.deleteByPublicId(publicId);
        } catch (EmptyResultDataAccessException e) {
            throw new ProductNotFoundException("Product not found: " + publicId);
        }
    }
}
