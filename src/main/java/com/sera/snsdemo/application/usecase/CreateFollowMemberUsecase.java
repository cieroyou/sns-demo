package com.sera.snsdemo.application.usecase;

import com.sera.snsdemo.domain.follow.service.FollowWriteService;
import com.sera.snsdemo.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFollowMemberUsecase {
    private final MemberReadService memberReadService;
    private final FollowWriteService followWriteservice;

    public void execute(Long fromMemberId, Long toMemberId) {
        var fromMember = memberReadService.get(fromMemberId);
        var toMember = memberReadService.get(toMemberId);

        followWriteservice.create(fromMember, toMember);
    }
}
