package com.sera.snsdemo.application.usecase;

import com.sera.snsdemo.domain.follow.dto.FollowDto;
import com.sera.snsdemo.domain.follow.service.FollowReadService;
import com.sera.snsdemo.domain.member.dto.MemberDto;
import com.sera.snsdemo.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFollowingMemberUsecase {
    private final MemberReadService memberReadService;
    private final FollowReadService followReadService;

    public List<MemberDto> execute(Long fromMemberId) {
        var followings = followReadService.getFollowings(fromMemberId);
        var followingIds = followings.stream().map(FollowDto::toMemberId).toList();
        return memberReadService.getMembers(followingIds);
    }

}
