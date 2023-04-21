package com.example.memberproductorder.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberId(String memberId);
}
