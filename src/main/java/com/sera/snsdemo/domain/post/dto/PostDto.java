package com.sera.snsdemo.domain.post.dto;

import com.sera.snsdemo.domain.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto {
    Long id;
    String contents;
    LocalDateTime createdAt;
    Long likeCount;

    public PostDto(Post post, Long postLikeCount) {
        this.id = post.getId();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
        this.likeCount = postLikeCount;
    }
}
