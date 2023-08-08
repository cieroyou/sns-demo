package com.sera.snsdemo.application.controller;

import com.sera.snsdemo.application.usecase.CreateFollowMemberUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/follow")

public class FollowController {

    private final CreateFollowMemberUsecase createFollowMemberUsecase;

    @PostMapping("/{fromMemberId}/{toMemberId}")
    public void register(@PathVariable Long fromMemberId, @PathVariable Long toMemberId){
        createFollowMemberUsecase.execute(fromMemberId,toMemberId);
    }
}
