package com.example.memberproductorder.product;

import org.springframework.util.Assert;

record AddProductRequest(String productName, int productPrice) {
    AddProductRequest {
        Assert.hasText(productName, "상품명은 필수입니다.");
        Assert.isTrue(productPrice > 0, "상품 가격은 0보다 커야 합니다.");
    }
}
