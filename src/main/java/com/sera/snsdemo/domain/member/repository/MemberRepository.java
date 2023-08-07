package com.sera.snsdemo.domain.member.repository;

import com.sera.snsdemo.domain.member.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    public Member save(Member member) {
        return Member.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .birthday(member.getBirthday())
                .build();
    }
}
