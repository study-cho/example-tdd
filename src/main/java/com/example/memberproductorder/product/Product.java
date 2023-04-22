package com.example.memberproductorder.product;

import org.springframework.util.Assert;

class Product {
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
