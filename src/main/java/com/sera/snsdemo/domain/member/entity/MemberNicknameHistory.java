package com.sera.snsdemo.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class MemberNicknameHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private String nickname;
    private LocalDateTime createdAt;

    @Builder
    public MemberNicknameHistory(Long memberId, String nickname, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.nickname = Objects.requireNonNull(nickname);
        this.createdAt = (createdAt == null) ? LocalDateTime.now() : createdAt;
    }

    public MemberNicknameHistory(Member member) {
        this.memberId = member.getId();
        this.nickname = member.getNickname();
        this.createdAt = (createdAt == null) ? LocalDateTime.now() : createdAt;
    }
}
