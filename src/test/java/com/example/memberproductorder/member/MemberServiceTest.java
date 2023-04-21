package com.example.memberproductorder.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    private MemberService memberService;
    private MemberRepository memberRepository;
    private MemberPort memberPort;

    @BeforeEach
    void setUp() {
        memberRepository = new MemberRepository();
        memberPort = new MemberAdapter(memberRepository);
        memberService = new MemberService(memberPort);
    }

    @Test
    @DisplayName("회원 등록")
    void join() {
        MemberJoinRequest request = MemberSteps.memberJoinRequest_create();
        memberService.join(request);
    }


}
