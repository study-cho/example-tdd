package com.example.memberproductorder.member;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class MemberSteps {
    static MemberJoinRequest memberJoinRequest_create() {
        String memberId = "test1";
        String memberName = "홍길동";
        return new MemberJoinRequest(memberId, memberName);
    }

    static ExtractableResponse<Response> memberJoinRequest(MemberJoinRequest request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/members/join")
                .then()
                .log().all().extract();
    }
}