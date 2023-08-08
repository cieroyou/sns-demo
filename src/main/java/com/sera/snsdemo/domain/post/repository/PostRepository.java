package com.sera.snsdemo.domain.post.repository;

import com.sera.snsdemo.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
//    Optional<Post> findById(Long id);

}

