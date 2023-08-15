package com.sera.snsdemo.application.usecase;

import com.sera.snsdemo.domain.follow.dto.FollowDto;
import com.sera.snsdemo.domain.follow.service.FollowReadService;
import com.sera.snsdemo.domain.post.entity.Post;
import com.sera.snsdemo.domain.post.service.PostReadService;
import com.sera.snsdemo.util.CursorRequest;
import com.sera.snsdemo.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTimelinePostsUsecase {
    private final PostReadService postReadService;
    private final FollowReadService followReadService;

    public PageCursor<Post> execute(Long memberId, CursorRequest cursorRequest) {
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(FollowDto::toMemberId).toList();
        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }

}
