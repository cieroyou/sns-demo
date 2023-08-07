package com.sera.snsdemo.domain.member.entity;

import com.sera.snsdemo.util.MemberFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    @DisplayName("회원은 닉네임을 변경할 수 있다")
    @Test
    void testChangeNickname() {
        var member = MemberFixture.NORMAL.getMember();
        var expected = "nick!";

        member.changeNickname(expected);

        Assertions.assertEquals(expected, member.getNickname());
    }

    @DisplayName("닉네임은 10자를 초과할 수 없다")
    @Test
    void testNicknameMaxLength() {
        var member = MemberFixture.NORMAL.getMember();
        var invalidOverMaxLength = "nick_must9length";

        Assertions.assertThrows(IllegalArgumentException.class, () -> member.changeNickname(invalidOverMaxLength));
    }

}