package com.example.memberproductorder.member;

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
