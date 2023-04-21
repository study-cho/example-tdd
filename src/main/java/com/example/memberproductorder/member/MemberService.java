package com.example.memberproductorder.member;

class MemberService {
    private final MemberPort memberPort;

    MemberService(MemberPort memberPort) {
        this.memberPort = memberPort;
    }

    public void join(MemberJoinRequest request) {
        Member member = new Member(request.memberId(), request.memberName(), MemberGrade.BASIC);

        memberPort.save(member);
    }

}
