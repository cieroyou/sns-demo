package com.sera.snsdemo.controller;

import com.sera.snsdemo.domain.member.dto.RegisterMemberCommand;
import com.sera.snsdemo.domain.member.entity.Member;
import com.sera.snsdemo.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberWriteService memberWriteService;

    @PostMapping("/members")
    public Member register(@RequestBody RegisterMemberCommand command){
        return memberWriteService.register(command);
    }
}
