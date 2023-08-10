package com.sera.snsdemo.application.controller;

import com.sera.snsdemo.application.usecase.CreateFollowMemberUsecase;
import com.sera.snsdemo.application.usecase.GetFollowingMemberUsecase;
import com.sera.snsdemo.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/follow")

public class FollowController {
    private final CreateFollowMemberUsecase createFollowMemberUsecase;
    private final GetFollowingMemberUsecase getFollowingMemberUsecase;

    @PostMapping("/{fromMemberId}/{toMemberId}")
    public void register(@PathVariable Long fromMemberId, @PathVariable Long toMemberId) {
        createFollowMemberUsecase.execute(fromMemberId, toMemberId);
    }

    @GetMapping("members/{fromMemberId}")
    public List<MemberDto> getFollowings(@PathVariable Long fromMemberId) {
        return getFollowingMemberUsecase.execute(fromMemberId);
    }
}
