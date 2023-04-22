package com.example.memberproductorder.product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ProductServiceTest {

    @Autowired private ProductService productService;

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
        final Long wrongId = 100L;

        //when
        //then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> productService.findProduct(wrongId));
        assertThat(exception.getMessage()).isEqualTo("잘못된 상품 아이디입니다.");
    }

    @Test
    @DisplayName("상품목록조회")
    void findAllProduct() {
        productService.addProduct(addProductRequest_create());
        productService.addProduct(addProductRequest_create());

        List<Product> list = productService.findAllProduct();

        list.forEach(System.out::println);
        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("상품수정")
    void updateProduct() {
        //given
        productService.addProduct(addProductRequest_create());
        Long productId = 1L;
        UpdateProductRequest request = new UpdateProductRequest("상품1", 2000);
        productService.updateProduct(productId, request);

        //when
        GetProductResponse response = productService.findProduct(productId);

        //then
        assertThat(response.productName()).isEqualTo("상품1");
        assertThat(response.productPrice()).isEqualTo(2000);
    }

}
