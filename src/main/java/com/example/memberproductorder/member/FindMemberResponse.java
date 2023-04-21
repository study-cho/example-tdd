package com.example.memberproductorder.member;

record FindMemberResponse(
        String memberId,
        String memberName,
        MemberGrade memberGrade
) {
}
