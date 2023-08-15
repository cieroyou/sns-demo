package com.sera.snsdemo.domain.post.entity;

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
public class Timeline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private Long postId;
    private LocalDateTime createdAt;

    @Builder
    public Timeline(Long memberId, Long postId, LocalDateTime createdAt) {
        this.memberId = Objects.requireNonNull(memberId);
        this.postId = Objects.requireNonNull(postId);
        this.createdAt = (createdAt == null) ? LocalDateTime.now() : createdAt;
    }
}