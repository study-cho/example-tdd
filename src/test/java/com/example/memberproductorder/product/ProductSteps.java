package com.example.memberproductorder.product;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class ProductSteps {
    static ExtractableResponse<Response> addProductRequest(AddProductRequest request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/products/add")
                .then()
                .log().all().extract();
    }

    static AddProductRequest addProductRequest_create() {
        String productName = "상품1";
        int productPrice = 1000;
        AddProductRequest request = new AddProductRequest(productName, productPrice);
        return request;
    }

    static ExtractableResponse<Response> findProductRequest(Long productId) {
        return RestAssured.given().log().all()
                .when()
                .get("/products/{productId}", productId)
                .then()
                .log().all().extract();
    }

    static ExtractableResponse<Response> findAllProductRequest() {
        return RestAssured.given().log().all()
                .when()
                .get("/products")
                .then()
                .log().all().extract();
    }

    static UpdateProductRequest updateProductRequest_create() {
        return new UpdateProductRequest("상품1", 2000);
    }

    static ExtractableResponse<Response> updateProductRequest(Long productId, UpdateProductRequest request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .patch("/products/{productId}", productId)
                .then()
                .log().all().extract();
    }
}