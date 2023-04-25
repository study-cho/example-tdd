package com.example.memberproductorder.order;

import com.example.memberproductorder.member.MemberSteps;
import com.example.memberproductorder.product.ProductSteps;

public class OrderStep {

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
}