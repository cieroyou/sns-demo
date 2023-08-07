package com.sera.snsdemo.domain.member.dto;

import com.sera.snsdemo.domain.member.entity.Member;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberDto {
    final Long id;
    final String email;
    final String nickname;
    final LocalDate birthday;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.birthday = member.getBirthday();
    }
}
