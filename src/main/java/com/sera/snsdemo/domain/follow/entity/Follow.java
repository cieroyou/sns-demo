package com.sera.snsdemo.domain.follow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fromMemberId;
    private Long toMemberId;
    private LocalDateTime createdAt;


    @Builder
    public Follow(Long fromMemberId, Long toMemberId, LocalDateTime createdAt) {
        this.fromMemberId = fromMemberId;
        this.toMemberId = toMemberId;
        this.createdAt = (createdAt == null) ? LocalDateTime.now() : createdAt;
    }
}
