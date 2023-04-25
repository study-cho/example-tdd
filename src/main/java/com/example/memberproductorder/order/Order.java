package com.example.memberproductorder.order;

import com.example.memberproductorder.member.Member;
import com.example.memberproductorder.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Member member;

    @OneToOne
    private Product product;

    private int quantity;
    private int totalPrice;

    public Order(Member member, Product product, int quantity) {
        this.member = member;
        this.product = product;
        this.quantity = quantity;
    }

    public void calculate() {
        this.totalPrice = member.getMemberGrade().applyDiscount(product.getProductPrice()) * quantity;
    }
}
