package com.example.memberproductorder.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired private MemberService memberService;

    @Test
    @DisplayName("회원 등록")
    void join() {
        MemberJoinRequest request = MemberSteps.memberJoinRequest_create();
        memberService.join(request);
    }

    @Test
    @DisplayName("회원 조회")
    void findMember() {
        //given
        memberService.join(MemberSteps.memberJoinRequest_create());
        Long id = 1L;

        //when
        FindMemberResponse response = memberService.findMember(id);

        //then
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("아이디 중복 조회")
    void duplicateId() {
        //given
        memberService.join(MemberSteps.memberJoinRequest_create());
        String memberId1 = "test1";
        String memberId2 = "test2";

        //when
        Boolean duplicate1 = memberService.duplicateMember(memberId1);
        Boolean duplicate2 = memberService.duplicateMember(memberId2);

        //then
        assertThat(duplicate1).isTrue();
        assertThat(duplicate2).isFalse();

    }


}
