package com.example.memberproductorder.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired private MemberService memberService;

    @Test
    @DisplayName("회원 등록")
    void join() {
        MemberJoinRequest request = MemberSteps.memberJoinRequest_create();
        memberService.join(request);
    }


}
