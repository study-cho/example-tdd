package com.example.memberproductorder.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

class ProductServiceTest {

    private ProductService productService;
    private ProductRepository productRepository;
    private ProductPort productPort;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        productPort = new ProductAdapter(productRepository);
        productService = new ProductService(productPort);
    }

    @Test
    @DisplayName("상품등록")
    void addProduct() {
        String productName = "상품1";
        int productPrice = 1000;
        AddProductRequest request = new AddProductRequest(productName, productPrice);
        productService.addProduct(request);
    }

    private record AddProductRequest(String productName, int productPrice) {
        private AddProductRequest {
            Assert.hasText(productName, "상품명은 필수입니다.");
            Assert.isTrue(productPrice > 0, "상품 가격은 0보다 커야 합니다.");
        }
    }

    private class ProductService {
        private final ProductPort productPort;

        private ProductService(ProductPort productPort) {
            this.productPort = productPort;
        }

        public void addProduct(AddProductRequest request) {
            Product product = new Product(request.productName(), request.productPrice());

            productPort.addProduct(product);
        }
    }

    private class Product {
        private Long id;
        private final String productName;
        private final int productPrice;

        public Product(String productName, int productPrice) {
            Assert.hasText(productName, "상품명은 필수입니다.");
            Assert.isTrue(productPrice > 0, "상품 가격은 0보다 커야 합니다.");
            this.productName = productName;
            this.productPrice = productPrice;
        }

        public void assignId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }

    private interface ProductPort {
        void addProduct(Product product);
    }

    class ProductAdapter implements ProductPort {
        private final ProductRepository productRepository;

        ProductAdapter(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        @Override
        public void addProduct(Product product) {
            productRepository.addProduct(product);
        }
    }


    private class ProductRepository {
        private Long sequence = 0L;
        private Map<Long, Product> persistence = new HashMap<>();

        public void addProduct(Product product) {
            product.assignId(++sequence);
            persistence.put(product.getId(), product);
        }
    }
}
