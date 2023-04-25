package com.example.memberproductorder.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public
class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private int productPrice;

    public Product(String productName, int productPrice) {
        Assert.hasText(productName, "상품명은 필수입니다.");
        Assert.isTrue(productPrice > 0, "상품 가격은 0보다 커야 합니다.");
        this.productName = productName;
        this.productPrice = productPrice;
    }

}
