package com.example.memberproductorder.order;

import com.example.memberproductorder.member.Member;
import com.example.memberproductorder.member.MemberGrade;
import com.example.memberproductorder.product.Product;

record GetOrderResponse(
        Long orderId,
        String memberId,
        MemberGrade memberGrade,
        String productName,
        int quantity,
        int totalPrice
) {
}
