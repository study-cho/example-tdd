package com.example.memberproductorder.member;

import org.springframework.util.Assert;

class Member {
    private Long id;
    private final String memberId;
    private final String memberName;
    private final MemberGrade memberGrade;

    public Member(String memberId, String memberName, MemberGrade memberGrade) {
        Assert.hasText(memberId, "회원 아이디는 필수입니다.");
        Assert.hasText(memberName, "회원 이름은 필수입니다.");
        Assert.notNull(memberGrade, "회원 등급은 필수입니다.");
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberGrade = memberGrade;
    }

    public void assignId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
