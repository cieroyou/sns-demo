package com.sera.snsdemo.domain.member.dto;

import com.sera.snsdemo.domain.member.entity.MemberNicknameHistory;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberNicknameHistoryDto {
    final Long id;
    final String nickname;

    final LocalDateTime createdAt;

    public MemberNicknameHistoryDto(MemberNicknameHistory memberNicknameHistory) {
        this.id = memberNicknameHistory.getId();
        this.nickname = memberNicknameHistory.getNickname();
        this.createdAt = memberNicknameHistory.getCreatedAt();
    }
}
