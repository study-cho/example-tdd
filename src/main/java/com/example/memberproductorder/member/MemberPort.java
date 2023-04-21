package com.example.memberproductorder.member;

interface MemberPort {
    void save(Member member);

    Member findMember(Long id);

    Member duplicateMemberId(String memberId);
}
