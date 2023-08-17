package com.sera.snsdemo.domain.post.service;

import com.sera.snsdemo.domain.member.dto.MemberDto;
import com.sera.snsdemo.domain.post.entity.Post;
import com.sera.snsdemo.domain.post.entity.PostLike;
import com.sera.snsdemo.domain.post.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeWriteService {
    private final PostLikeRepository postLikeRepository;

    public void create(Post post, MemberDto memberDto) {
        var postLike = PostLike.builder()
                .memberId(memberDto.getId())
                .postId(post.getId())
                .build();
        postLikeRepository.save(postLike);
    }
}
