package com.example.memberproductorder.order;

import com.example.memberproductorder.member.Member;
import com.example.memberproductorder.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
class OrderService {

    private final OrderPort orderPort;

    OrderService(OrderPort orderPort) {
        this.orderPort = orderPort;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderRequest request) {
        Member member = orderPort.getMemberById(request.mId());
        Product product = orderPort.getProductById(request.productId());
        Order order = new Order(member, product, request.quantity());
        order.calculate();

        orderPort.save(order);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<GetOrderResponse> getOrderById(@PathVariable Long orderId) {
        Order order = orderPort.getOrderById(orderId);

        GetOrderResponse response = new GetOrderResponse(
                order.getId(),
                order.getMember().getMemberId(),
                order.getMember().getMemberGrade(),
                order.getProduct().getProductName(),
                order.getProduct().getProductPrice(),
                order.getQuantity(),
                order.getTotalPrice()
        );

        return ResponseEntity.ok(response);
    }
}
