package com.sera.snsdemo.controller;

import com.sera.snsdemo.domain.member.dto.MemberDto;
import com.sera.snsdemo.domain.member.dto.RegisterMemberCommand;
import com.sera.snsdemo.domain.member.service.MemberReadService;
import com.sera.snsdemo.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberWriteService memberWriteService;
    private final MemberReadService memberReadService;

    @PostMapping("/members")
    public MemberDto register(@RequestBody RegisterMemberCommand command) {
        var member = memberWriteService.register(command);
        return new MemberDto(member);
    }
    @GetMapping("/members/{id}")
    public MemberDto get(@PathVariable Long id) {
        return memberReadService.get(id);
    }

    @PostMapping("/members/{id}/nickname")
    public MemberDto changeNickname(@PathVariable Long id, @RequestBody String nickname){
        memberWriteService.changeNickname(id, nickname);
        return memberReadService.get(id);
    }
}
