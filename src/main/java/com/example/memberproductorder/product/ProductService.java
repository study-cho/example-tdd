package com.example.memberproductorder.product;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
class ProductService {
    private final ProductPort productPort;

    ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }

    @Transactional
    public void addProduct(AddProductRequest request) {
        Product product = new Product(request.productName(), request.productPrice());

        productPort.addProduct(product);
    }

    public GetProductResponse findProduct(Long productId) {
        Product product = productPort.findProduct(productId);

        return new GetProductResponse(
                product.getId(),
                product.getProductName(),
                product.getProductPrice()
        );
    }

    @Transactional
    public void updateProduct(Long productId, UpdateProductRequest request) {
        productPort.updateProduct(productId, request);
    }

    public List<Product> findAllProduct() {
        return productPort.findAllProduct();
    }
}
