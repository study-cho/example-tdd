package com.example.memberproductorder.product;

import java.util.List;
import java.util.Optional;

interface ProductPort {
    void addProduct(Product product);

    Optional<Product> findProduct(Long productId);

    void updateProduct(Long productId, UpdateProductRequest request);

    List<Product> findAllProduct();
}
