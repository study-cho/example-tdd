package com.example.memberproductorder.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

interface MemberRepository extends JpaRepository<Member, Long> {
}
