package com.example.memberproductorder.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
class ProductService {
    private final ProductPort productPort;

    ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<Void> addProduct(@RequestBody AddProductRequest request) {
        Product product = new Product(request.productName(), request.productPrice());

        productPort.addProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetProductResponse> findProduct(@PathVariable Long productId) {
        Product product = productPort.findProduct(productId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 상품 아이디입니다."));

        GetProductResponse response = new GetProductResponse(
                product.getId(),
                product.getProductName(),
                product.getProductPrice()
        );

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{productId}")
    @Transactional
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId,
                              @RequestBody UpdateProductRequest request) {
        productPort.updateProduct(productId, request);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> productList = productPort.findAllProduct();

        return ResponseEntity.ok(productList);
    }
}
