package com.example.memberproductorder.member;

import com.example.memberproductorder.ApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class MemberApiTest extends ApiTest {

    @Test
    @DisplayName("회원 등록")
    void join() {
        var request = MemberSteps.memberJoinRequest_create();

        var response = MemberSteps.memberJoinRequest(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("회원 조회")
    void findMember() {
        // given
        MemberSteps.memberJoinRequest(MemberSteps.memberJoinRequest_create());
        Long id = 1L;

        //when
        var response = MemberSteps.memberFindRequest(id);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("memberId")).isEqualTo("test1");

    }

}
