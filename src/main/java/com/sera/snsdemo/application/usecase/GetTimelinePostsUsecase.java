package com.sera.snsdemo.application.usecase;

import com.sera.snsdemo.domain.follow.dto.FollowDto;
import com.sera.snsdemo.domain.follow.service.FollowReadService;
import com.sera.snsdemo.domain.post.entity.Post;
import com.sera.snsdemo.domain.post.entity.Timeline;
import com.sera.snsdemo.domain.post.service.PostReadService;
import com.sera.snsdemo.domain.post.service.TimelineReadService;
import com.sera.snsdemo.util.CursorRequest;
import com.sera.snsdemo.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTimelinePostsUsecase {
    private final PostReadService postReadService;
    private final FollowReadService followReadService;
    private final TimelineReadService timelineReadService;

    public PageCursor<Post> execute(Long memberId, CursorRequest cursorRequest) {
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(FollowDto::toMemberId).toList();
        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }

    /**
     * 게시글을 작성하면 Timeline 테이블에 자신을 팔로잉하는 멤버들과 작성한 게시글을 맵핑 인서트가되고, 해당 Timeline 테이블만 쿼리하여 데이터를 가져오는 방식으로 구현됨
     * 1. Timeline 테이블<memberId, postId> 에서 memberId 로 조회한다.
     * 2. 1번에 해당하는 게시글을 가져온다.
     */
    public PageCursor<Post> executeByTimeline(Long memberId, CursorRequest cursorRequest) {
        var pagedTimelines = timelineReadService.getTimelines(memberId, cursorRequest);
        var postIds = pagedTimelines.body().stream().map(Timeline::getPostId).toList();
        var posts = postReadService.getPosts(postIds);
        return new PageCursor<>(pagedTimelines.nextCursorRequest(), posts);
    }


}
