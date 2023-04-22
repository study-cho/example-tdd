package com.example.memberproductorder.product;

import java.util.List;

class ProductService {
    private final ProductPort productPort;

    ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }

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

    public void updateProduct(Long productId, UpdateProductRequest request) {
        productPort.updateProduct(productId, request);
    }

    public List<Product> findAllProduct() {
        return productPort.findAllProduct();
    }
}
