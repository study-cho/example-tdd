package com.example.memberproductorder.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String memberId;
    private String memberName;
    private MemberGrade memberGrade;

    public Member(String memberId, String memberName, MemberGrade memberGrade) {
        Assert.hasText(memberId, "회원 아이디는 필수입니다.");
        Assert.hasText(memberName, "회원 이름은 필수입니다.");
        Assert.notNull(memberGrade, "회원 등급은 필수입니다.");
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberGrade = memberGrade;
    }

}
