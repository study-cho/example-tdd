package com.example.memberproductorder.member;

import org.springframework.util.Assert;

record MemberJoinRequest(String memberId, String memberName) {
    MemberJoinRequest {
        Assert.hasText(memberId, "회원 아이디는 필수입니다.");
        Assert.hasText(memberName, "회원 이름은 필수입니다.");
    }
}
