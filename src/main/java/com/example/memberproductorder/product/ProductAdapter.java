package com.example.memberproductorder.product;

import java.util.List;

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
    public Product findProduct(Long productId) {
        return productRepository.findProduct(productId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 상품 아이디입니다."));
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
