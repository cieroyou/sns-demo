package com.sera.snsdemo.domain.post.service;

import com.sera.snsdemo.domain.post.dto.PostCommand;
import com.sera.snsdemo.domain.post.entity.Post;
import com.sera.snsdemo.domain.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostWriteService {
    private final PostRepository postRepository;

    public Long create(PostCommand command) {
        var post = Post.builder()
                .memberId(command.memberId())
                .contents(command.contents())
                .build();
        postRepository.save(post);
        return post.getId();
    }

    public void likePost(Long postId) {
        var post = postRepository.findById(postId)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("해당 Post(%d)가 존재하지 않습니다.", postId)));
        post.incrementLikeCount();
        postRepository.save(post);
    }
}
