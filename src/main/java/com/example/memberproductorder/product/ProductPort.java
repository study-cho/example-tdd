package com.example.memberproductorder.product;

import java.util.List;

interface ProductPort {
    void addProduct(Product product);

    Product findProduct(Long productId);

    void updateProduct(Long productId, UpdateProductRequest request);

    List<Product> findAllProduct();
}
