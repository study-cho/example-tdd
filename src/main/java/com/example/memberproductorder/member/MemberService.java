package com.example.memberproductorder.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<FindMemberResponse> findMember(@PathVariable Long id) {
        Member member = memberPort.findMember(id);

        FindMemberResponse response = new FindMemberResponse(
                member.getMemberId(),
                member.getMemberName(),
                member.getMemberGrade()
        );

        return ResponseEntity.ok(response);
    }

}
