package com.example.memberproductorder.order;

import com.example.memberproductorder.member.Member;
import com.example.memberproductorder.product.Product;

public interface OrderPort {
    Member getMemberById(Long mId);

    Product getProductById(Long productId);

    void save(Order order);

    Order getOrderById(Long orderId);

}
