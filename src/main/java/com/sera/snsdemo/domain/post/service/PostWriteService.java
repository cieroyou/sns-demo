package com.sera.snsdemo.domain.post.service;

import com.sera.snsdemo.domain.post.dto.PostCommand;
import com.sera.snsdemo.domain.post.entity.Post;
import com.sera.snsdemo.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
