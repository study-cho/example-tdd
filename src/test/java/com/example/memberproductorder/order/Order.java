package com.example.memberproductorder.order;

import com.example.memberproductorder.member.Member;
import com.example.memberproductorder.product.Product;

class Order {
    private Long id;
    private final Member member;
    private final Product product;
    private final int quantity;
    private int totalPrice;

    public Order(Member member, Product product, int quantity) {
        this.member = member;
        this.product = product;
        this.quantity = quantity;
    }

    public void calculate() {
        this.totalPrice = member.getMemberGrade().applyDiscount(product.getProductPrice()) * quantity;
    }

    public void assignId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Member getMember() {
        return member;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
