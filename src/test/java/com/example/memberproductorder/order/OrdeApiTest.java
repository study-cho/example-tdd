package com.example.memberproductorder.order;

import com.example.memberproductorder.ApiTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class OrdeApiTest extends ApiTest {

    @Test
    @DisplayName("상품주문")
    void productOrder() {

        CreateOrderRequest request = OrderSteps.orderRequest_create();

        ExtractableResponse<Response> response = OrderSteps.getOrderRequest(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    @DisplayName("주문번호로 가져오기")
    void getById() {

        OrderSteps.getOrderRequest(OrderSteps.orderRequest_create());
        Long orderId = 1L;

        ExtractableResponse<Response> response = OrderSteps.findOrderRequest(orderId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("memberId")).isEqualTo("test1");

    }


}