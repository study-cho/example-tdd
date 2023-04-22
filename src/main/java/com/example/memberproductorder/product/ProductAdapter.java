package com.example.memberproductorder.product;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class ProductAdapter implements ProductPort {
    private final ProductRepository productRepository;

    ProductAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    @Override
    public Optional<Product> findProduct(Long productId) {
        return productRepository.findProduct(productId);
    }

    @Override
    public void updateProduct(Long productId, UpdateProductRequest request) {
        Product product = new Product(request.productName(), request.productPrice());
        productRepository.update(productId, product);
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAllProduct();
    }
}
