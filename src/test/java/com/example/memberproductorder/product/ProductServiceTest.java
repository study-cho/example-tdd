package com.example.memberproductorder.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        AddProductRequest request = addProductRequest_create();
        productService.addProduct(request);
    }

    private static AddProductRequest addProductRequest_create() {
        String productName = "상품1";
        int productPrice = 1000;
        AddProductRequest request = new AddProductRequest(productName, productPrice);
        return request;
    }

    @Test
    @DisplayName("상품조회")
    void findProduct() {
        //given
        productService.addProduct(addProductRequest_create());
        final Long productId = 1L;

        //when
        GetProductResponse response = productService.findProduct(productId);

        //then
        assertThat(response).isNotNull();
        assertThat(response.productName()).isEqualTo("상품1");
    }

    @Test
    @DisplayName("상품조회_wrongId")
    void findProduct_throw() {
        //given
        final Long wrongId = 1L;

        //when
        //then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> productService.findProduct(wrongId));
        assertThat(exception.getMessage()).isEqualTo("잘못된 상품 아이디입니다.");

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

        public GetProductResponse findProduct(Long productId) {
            Product product = productPort.findProduct(productId);

            return new GetProductResponse(
                    product.getId(),
                    product.getProductName(),
                    product.getProductPrice()
            );
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

        public String getProductName() {
            return productName;
        }

        public int getProductPrice() {
            return productPrice;
        }
    }

    private interface ProductPort {
        void addProduct(Product product);
        Product findProduct(Long productId);
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

        @Override
        public Product findProduct(Long productId) {
            return productRepository.findProduct(productId)
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 상품 아이디입니다."));
        }
    }


    private class ProductRepository {
        private Long sequence = 0L;
        private Map<Long, Product> persistence = new HashMap<>();

        public void addProduct(Product product) {
            product.assignId(++sequence);
            persistence.put(product.getId(), product);
        }

        public Optional<Product> findProduct(Long productId) {
            return Optional.ofNullable(persistence.get(productId));
        }
    }

    private record GetProductResponse(
            Long id,
            String productName,
            int productPrice
    ) {
    }
}
