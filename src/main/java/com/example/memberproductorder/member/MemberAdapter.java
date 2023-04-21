package com.example.memberproductorder.member;

import org.springframework.stereotype.Component;

@Component
class MemberAdapter implements MemberPort {
    private final MemberRepository memberRepository;

    MemberAdapter(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }
}
