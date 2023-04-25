package com.example.memberproductorder.order;

import org.springframework.util.Assert;

record CreateOrderRequest(Long mId, Long productId, int quantity) {
    public CreateOrderRequest {
        Assert.notNull(mId, "회원번호는 필수입니다.");
        Assert.notNull(productId, "상품 ID는 필수입니다.");
        Assert.isTrue(quantity > 0, "수량은 0보다 커야 합니다.");
    }
}
