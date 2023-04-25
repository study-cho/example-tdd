package com.example.memberproductorder.order;

import com.example.memberproductorder.member.Member;
import com.example.memberproductorder.member.MemberGrade;
import com.example.memberproductorder.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderServiceTest {

    @Autowired private OrderService orderService;

    @Test
    @DisplayName("상품주문")
    void productOrder() {

        CreateOrderRequest request = OrderStep.simpleOrderRequest_create();
        orderService.createOrder(request);
    }

    @Test
    @DisplayName("주문번호로 가져오기")
    void getById() {

        orderService.createOrder(OrderStep.simpleOrderRequest_create());
        Long orderId = 1L;

        GetOrderResponse response = orderService.getOrderById(orderId);

        assertThat(response.memberId()).isEqualTo("test1");
        assertThat(response.memberGrade()).isEqualTo(MemberGrade.VIP);
        assertThat(response.productName()).isEqualTo("상품1");
        assertThat(response.totalPrice()).isEqualTo(2000);

    }

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