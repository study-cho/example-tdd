package com.example.memberproductorder.product;

import com.example.memberproductorder.ApiTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class ProductApiTest extends ApiTest {

    @Autowired ProductRepository productRepository;

    @Test
    @DisplayName("상품등록")
    void addProduct() {
        AddProductRequest request = ProductSteps.addProductRequest_create();

        ExtractableResponse<Response> response = ProductSteps.addProductRequest(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("상품조회")
    void findProduct() {
        //given
        ProductSteps.addProductRequest(ProductSteps.addProductRequest_create());
        final Long productId = 1L;

        //when
        ExtractableResponse<Response> response = ProductSteps.findProductRequest(productId);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("productName")).isEqualTo("상품1");
    }

    @Test
    @DisplayName("상품조회_wrongId")
    void findProduct_throw() {
        //given
        final Long wrongId = 100L;

        ExtractableResponse<Response> response = ProductSteps.findProductRequest(wrongId);
        assertThat(response.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());

//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                () -> findProductRequest(wrongId));
//        assertThat(exception.getMessage()).isEqualTo("잘못된 상품 아이디입니다.");

    }

    @Test
    @DisplayName("상품목록조회")
    void findAllProduct() {
        ProductSteps.addProductRequest(ProductSteps.addProductRequest_create());
        ProductSteps.addProductRequest(ProductSteps.addProductRequest_create());

        ExtractableResponse<Response> response = ProductSteps.findAllProductRequest();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("상품수정")
    void updateProduct() {
        //given
        ProductSteps.addProductRequest(ProductSteps.addProductRequest_create());
        Long productId = 1L;
        UpdateProductRequest request = ProductSteps.updateProductRequest_create();

        ExtractableResponse<Response> response = ProductSteps.updateProductRequest(productId, request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(productRepository.findProduct(productId).get().getProductPrice()).isEqualTo(2000);
    }

}
