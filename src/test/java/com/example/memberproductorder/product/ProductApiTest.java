package com.example.memberproductorder.product;

import com.example.memberproductorder.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

class ProductApiTest extends ApiTest {

    @Autowired ProductRepository productRepository;

    @Test
    @DisplayName("상품등록")
    void addProduct() {
        AddProductRequest request = addProductRequest_create();

        ExtractableResponse<Response> response = addProductRequest(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private static ExtractableResponse<Response> addProductRequest(AddProductRequest request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/products/add")
                .then()
                .log().all().extract();
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
        addProductRequest(addProductRequest_create());
        final Long productId = 1L;

        //when
        ExtractableResponse<Response> response = findProductRequest(productId);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("productName")).isEqualTo("상품1");
    }

    private static ExtractableResponse<Response> findProductRequest(Long productId) {
        return RestAssured.given().log().all()
                .when()
                .get("/products/{productId}", productId)
                .then()
                .log().all().extract();
    }

    @Test
    @DisplayName("상품조회_wrongId")
    void findProduct_throw() {
        //given
        final Long wrongId = 100L;

        ExtractableResponse<Response> response = findProductRequest(wrongId);
        assertThat(response.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());

//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                () -> findProductRequest(wrongId));
//        assertThat(exception.getMessage()).isEqualTo("잘못된 상품 아이디입니다.");

    }

    @Test
    @DisplayName("상품목록조회")
    void findAllProduct() {
        addProductRequest(addProductRequest_create());
        addProductRequest(addProductRequest_create());

        ExtractableResponse<Response> response = findAllProductRequest();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private static ExtractableResponse<Response> findAllProductRequest() {
        return RestAssured.given().log().all()
                .when()
                .get("/products")
                .then()
                .log().all().extract();
    }

    @Test
    @DisplayName("상품수정")
    void updateProduct() {
        //given
        addProductRequest(addProductRequest_create());
        Long productId = 1L;
        UpdateProductRequest request = new UpdateProductRequest("상품1", 2000);

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .patch("/products/{productId}", productId)
                .then()
                .log().all().extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(productRepository.findProduct(productId).get().getProductPrice()).isEqualTo(2000);
    }

}
