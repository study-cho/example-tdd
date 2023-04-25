package com.example.memberproductorder.order;

import com.example.memberproductorder.member.Member;
import com.example.memberproductorder.member.MemberGrade;
import com.example.memberproductorder.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        StubOrderPort orderPort = new StubOrderPort();
        orderService = new OrderService(orderPort);
    }

    @Test
    @DisplayName("상품주문")
    void productOrder() {

        CreateOrderRequest request = orderRequest_create();
        orderService.createOrder(request);

    }

    @Test
    @DisplayName("주문번호로 가져오기")
    void getById() {

        orderService.createOrder(orderRequest_create());
        Long orderId = 1L;

        GetOrderResponse response = orderService.getOrderById(orderId);

        assertThat(response.memberId()).isEqualTo("test1");
        assertThat(response.memberGrade()).isEqualTo(MemberGrade.VIP);
        assertThat(response.productName()).isEqualTo("상품1");
        assertThat(response.totalPrice()).isEqualTo(2000);

    }

    private static CreateOrderRequest orderRequest_create() {
        Long member_id = 1L;
        Long productId = 1L;
        int quantity = 2;

        return new CreateOrderRequest(member_id, productId, quantity);
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


    private class StubOrderPort implements OrderPort {

        private final OrderRepository orderRepository;

        private StubOrderPort() {
            orderRepository = new OrderRepository();
        }

        @Override
        public Member getMemberById(Long mId) {
            return new Member("test1", "홍길동", MemberGrade.VIP);
        }

        @Override
        public Product getProductById(Long productId) {
            return new Product("상품1", 2000);
        }

        @Override
        public void save(Order order) {
            orderRepository.save(order);
        }

        @Override
        public Order getOrderById(Long orderId) {
            return orderRepository.findById(orderId);
        }
    }

}