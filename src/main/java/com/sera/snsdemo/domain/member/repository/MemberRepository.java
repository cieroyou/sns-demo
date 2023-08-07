package com.sera.snsdemo.domain.member.repository;

import com.sera.snsdemo.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {


}

