package com.ezbuy.product.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezbuy.product.model.Product;
import com.ezbuy.product.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findByPublicId(UUID publicId){
        return productRepository.findByPublicId(publicId).orElse(null);
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(UUID publicId, Product product) {
        Product existing = findByPublicId(publicId);
        product.setId(existing.getId());
        return productRepository.save(product);
    }

    public void delete(UUID publicId){
        productRepository.deleteByPublicId(publicId);
    }
}
