package com.sera.snsdemo.domain.post.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private String contents;
    private LocalDate createdDate;

    private Long likeCount;
    private LocalDateTime createdAt;

    @Builder
    public Post(Long memberId, String contents, LocalDate createdDate, Long likeCount, LocalDateTime createdAt) {
        this.memberId = Objects.requireNonNull(memberId);
        this.contents = Objects.requireNonNull(contents);
        this.createdDate = (createdDate == null) ? LocalDate.now() : createdDate;
        this.likeCount = (likeCount == null) ? 0 : likeCount;
        this.createdAt = (createdAt == null) ? LocalDateTime.now() : createdAt;
    }

    public void incrementLikeCount(){
        if(this.likeCount == null) this.likeCount = 1L;
        else{
            this.likeCount += 1;
        }
    }
}