package com.example.memberproductorder.order;

import com.example.memberproductorder.member.Member;
import com.example.memberproductorder.product.Product;
import org.springframework.stereotype.Component;

@Component
class OrderService {
    private final OrderPort orderPort;

    OrderService(OrderPort orderPort) {
        this.orderPort = orderPort;
    }

    public void createOrder(CreateOrderRequest request) {
        Member member = orderPort.getMemberById(request.mId());
        Product product = orderPort.getProductById(request.productId());
        Order order = new Order(member, product, request.quantity());
        order.calculate();

        orderPort.save(order);
    }

    public GetOrderResponse getOrderById(Long orderId) {
        Order order = orderPort.getOrderById(orderId);

        return new GetOrderResponse(
                order.getId(),
                order.getMember().getMemberId(),
                order.getMember().getMemberGrade(),
                order.getProduct().getProductName(),
                order.getQuantity(),
                order.getTotalPrice()
        );
    }
}
