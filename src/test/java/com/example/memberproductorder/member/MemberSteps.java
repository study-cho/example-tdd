package com.example.memberproductorder.member;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class MemberSteps {
    public static MemberJoinRequest memberJoinRequest_create() {
        String memberId = "test1";
        String memberName = "홍길동";
        return new MemberJoinRequest(memberId, memberName);
    }

    public static ExtractableResponse<Response> memberJoinRequest(MemberJoinRequest request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/members/join")
                .then()
                .log().all().extract();
    }

    static ExtractableResponse<Response> memberFindRequest(Long id) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when()
                .get("/members/{id}", id)
                .then().log().all()
                .extract();
        return response;
    }
}