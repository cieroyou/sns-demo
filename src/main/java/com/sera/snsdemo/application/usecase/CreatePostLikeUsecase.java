package com.sera.snsdemo.application.usecase;

import com.sera.snsdemo.domain.member.service.MemberReadService;
import com.sera.snsdemo.domain.post.service.PostLikeWriteService;
import com.sera.snsdemo.domain.post.service.PostReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostLikeUsecase {
    private final PostReadService postReadService;
    private final PostLikeWriteService postLikeWriteService;
    private final MemberReadService memberReadService;

    public void execute(Long postId, Long memberId) {
        var post = postReadService.getPost(postId);
        var member = memberReadService.get(memberId);
        postLikeWriteService.create(post, member);
    }
}
