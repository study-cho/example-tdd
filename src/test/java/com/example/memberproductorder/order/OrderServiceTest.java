package com.example.memberproductorder.order;

import com.example.memberproductorder.member.Member;
import com.example.memberproductorder.member.MemberGrade;
import com.example.memberproductorder.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {

    @Test
    @DisplayName("금액계산_VIP_1000원 할인")
    void getTotalPrice_vip() {
        Member member = new Member("test1", "홍길동", MemberGrade.VIP);
        Product product = new Product("상품1", 2000);

        Order order = new Order(member,product,2);
        order.calculate();

        assertThat(order.getTotalPrice()).isEqualTo(2000);
    }
    @Test
    @DisplayName("금액계산_BASIC_none")
    void getTotalPrice_basic() {
        Member member = new Member("test1", "홍길동", MemberGrade.BASIC);
        Product product = new Product("상품1", 2000);

        Order order = new Order(member,product,2);
        order.calculate();

        assertThat(order.getTotalPrice()).isEqualTo(4000);
    }


}