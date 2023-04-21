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

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
    }

    @Override
    public Member duplicateMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }
}
