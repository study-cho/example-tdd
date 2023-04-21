package com.example.memberproductorder.member;

public class MemberSteps {
    static MemberJoinRequest memberJoinRequest_create() {
        String memberId = "test1";
        String memberName = "홍길동";
        return new MemberJoinRequest(memberId, memberName);
    }
}