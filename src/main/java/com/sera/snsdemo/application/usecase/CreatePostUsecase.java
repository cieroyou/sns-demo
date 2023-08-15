package com.sera.snsdemo.application.usecase;

import com.sera.snsdemo.domain.follow.dto.FollowDto;
import com.sera.snsdemo.domain.follow.service.FollowReadService;
import com.sera.snsdemo.domain.post.dto.PostCommand;
import com.sera.snsdemo.domain.post.service.PostWriteService;
import com.sera.snsdemo.domain.post.service.TimelineWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostUsecase {
    private final FollowReadService followReadService;
    private final PostWriteService postWriteService;
    private final TimelineWriteService timelineWriteService;

    public void execute(PostCommand postCommand) {
        var postId = postWriteService.create(postCommand);
        var followerMemberIds = followReadService.getFollowers(postCommand.memberId())
                .stream().map(FollowDto::fromMemberId).toList();
        timelineWriteService.deliveryTimeline(postId, followerMemberIds);
    }
}
