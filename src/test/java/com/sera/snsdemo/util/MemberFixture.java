package com.sera.snsdemo.util;

import com.sera.snsdemo.domain.member.entity.Member;

import java.time.LocalDate;

public enum MemberFixture {
    NORMAL("유저 닉네임", "oir232@gmail.com", "1991-01-02"),
    ;

    private final String nickname;
    private final String email;
    private final String birthday;

    MemberFixture(String nickname, String email, String birthday) {
        this.nickname = nickname;
        this.email = email;
        this.birthday = birthday;

    }

    public Member getMember() {
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .birthday(LocalDate.parse(birthday))
                .build();
    }
}
