package com.example.memberproductorder.order;

import com.example.memberproductorder.member.MemberSteps;
import com.example.memberproductorder.product.ProductSteps;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class OrderSteps {

    static CreateOrderRequest simpleOrderRequest_create() {
        Long member_id = 1L;
        Long productId = 1L;
        int quantity = 2;

        return new CreateOrderRequest(member_id, productId, quantity);
    }

    static CreateOrderRequest orderRequest_create() {
        MemberSteps.memberJoinRequest(MemberSteps.memberJoinRequest_create());
        Long member_id = 1L;

        ProductSteps.addProductRequest(ProductSteps.addProductRequest_create());
        Long productId = 1L;

        int quantity = 2;

        return new CreateOrderRequest(member_id, productId, quantity);
    }

    static ExtractableResponse<Response> getOrderRequest(CreateOrderRequest request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/orders/create")
                .then()
                .log().all().extract();
    }

    static ExtractableResponse<Response> findOrderRequest(Long orderId) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when()
                .get("orders/{orderId}", orderId)
                .then()
                .log().all().extract();
        return response;
    }
}