package com.example.memberproductorder.product;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
class ProductRepository {
    private Long sequence = 0L;
    private Map<Long, Product> persistence = new HashMap<>();

    public void addProduct(Product product) {
        product.assignId(++sequence);
        persistence.put(product.getId(), product);
    }

    public Optional<Product> findProduct(Long productId) {
        return Optional.ofNullable(persistence.get(productId));
    }

    public void update(Long productId, Product product) {
        product.assignId(productId);
        persistence.put(productId, product);
    }

    public List<Product> findAllProduct() {
        return persistence.values().stream().toList();
    }
}
