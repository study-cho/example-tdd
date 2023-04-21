package com.example.memberproductorder.member;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
class MemberRepository {
    private final Map<Long, Member> persistence = new HashMap<>();
    private Long sequence = 0L;

    public void save(Member member) {
        member.assignId(++sequence);
        persistence.put(member.getId(), member);
    }
}
