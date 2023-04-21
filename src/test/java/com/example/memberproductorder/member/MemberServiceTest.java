package com.example.memberproductorder.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

class MemberServiceTest {

    private MemberService memberService;
    private MemberRepository memberRepository;
    private MemberPort memberPort;

    @BeforeEach
    void setUp() {
        memberRepository = new MemberRepository();
        memberPort = new MemberAdapter(memberRepository);
        memberService = new MemberService(memberPort);
    }

    @Test
    @DisplayName("회원 등록")
    void join() {
        String memberId = "test1";
        String memberName = "홍길동";
        MemberJoinRequest request = new MemberJoinRequest(memberId, memberName);

        memberService.join(request);
    }


    private class MemberService {
        private final MemberPort memberPort;

        private MemberService(MemberPort memberPort) {
            this.memberPort = memberPort;
        }

        public void join(MemberJoinRequest request) {
            Member member = new Member(request.memberId(), request.memberName(), MemberGrade.BASIC);

            memberPort.save(member);
        }

    }

    private record MemberJoinRequest(String memberId, String memberName) {
        private MemberJoinRequest {
            Assert.hasText(memberId, "회원 아이디는 필수입니다.");
            Assert.hasText(memberName, "회원 이름은 필수입니다.");
        }
    }

    private class Member {
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

    private enum MemberGrade {
        BASIC,
        VIP,
    }

    private interface MemberPort {
        void save(Member member);
    }

    private class MemberAdapter implements MemberPort {
        private final MemberRepository memberRepository;

        private MemberAdapter(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }

        @Override
        public void save(Member member) {
            memberRepository.save(member);
        }
    }

    private class MemberRepository {
        private final Map<Long, Member> persistence = new HashMap<>();
        private Long sequence = 0L;

        public void save(Member member) {
            member.assignId(++sequence);
            persistence.put(member.getId(), member);
        }
    }

}
