package com.sera.snsdemo.domain.post.repository;

import com.sera.snsdemo.domain.post.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Long countByPostId(Long postId);
}

