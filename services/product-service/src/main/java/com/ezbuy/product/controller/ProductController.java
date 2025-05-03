package com.ezbuy.product.controller;

import com.ezbuy.product.model.Product;
import com.ezbuy.product.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{publicId}")
    public ResponseEntity<Product> getById(@PathVariable UUID publicId) {
        Product product = productService.findByPublicId(publicId);
        return (product != null) ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        Product created = productService.create(product);
        return ResponseEntity
                .created(URI.create("/products/" + created.getPublicId()))
                .body(created);
    }

    @PutMapping("/{publicId}")
    public ResponseEntity<Product> update(@PathVariable UUID publicId, @Valid @RequestBody Product product) {
        Product updated = productService.update(publicId, product);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<Void> delete(@PathVariable UUID publicId) {
        productService.delete(publicId);
        return ResponseEntity.noContent().build();
    }
}
