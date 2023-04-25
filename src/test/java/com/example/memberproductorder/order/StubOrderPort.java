package com.example.memberproductorder.order;

import com.example.memberproductorder.member.Member;
import com.example.memberproductorder.member.MemberGrade;
import com.example.memberproductorder.product.Product;
import org.springframework.stereotype.Component;

@Component
class StubOrderPort implements OrderPort {

    private final OrderRepository orderRepository;

    StubOrderPort() {
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
