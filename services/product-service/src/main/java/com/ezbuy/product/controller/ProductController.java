package com.ezbuy.product.controller;

import com.ezbuy.product.dto.ProductRequestDTO;
import com.ezbuy.product.dto.ProductResponseDTO;
import com.ezbuy.product.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{publicId}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable UUID publicId) {
        ProductResponseDTO product = productService.findByPublicId(publicId);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequestDTO product) {
        ProductResponseDTO created = productService.create(product);
        return ResponseEntity
                .created(URI.create("/products/" + created.publicId()))
                .body(created);
    }

    @PutMapping("/{publicId}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable UUID publicId, @RequestBody ProductRequestDTO product) {
        ProductResponseDTO updated = productService.update(publicId, product);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<Void> delete(@PathVariable UUID publicId) {
        productService.delete(publicId);
        return ResponseEntity.noContent().build();
    }
}
