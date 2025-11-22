package com.projectA.service;

import com.projectA.dto.ProductDTO;
import com.projectA.entity.Product;
import com.projectA.exception.ProductNotFoundException;
import com.projectA.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(ProductDTO dto) {
        Product p = new Product(dto.name(), dto.price(), dto.quantity());
        return repository.save(p);
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product update(Long id, ProductDTO dto) {
        Product existing = findById(id);
        existing.setName(dto.name());
        existing.setPrice(dto.price());
        existing.setQuantity(dto.quantity());
        return repository.save(existing);
    }

    public void delete(Long id) {
        Product existing = findById(id);
        repository.delete(existing);
    }

    public java.util.List<Product> findAll() {
        return repository.findAll();
    }
}

