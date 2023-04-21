package com.example.memberproductorder.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
class MemberService {
    private final MemberPort memberPort;

    MemberService(MemberPort memberPort) {
        this.memberPort = memberPort;
    }

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody MemberJoinRequest request) {
        Member member = new Member(request.memberId(), request.memberName(), MemberGrade.BASIC);

        memberPort.save(member);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
