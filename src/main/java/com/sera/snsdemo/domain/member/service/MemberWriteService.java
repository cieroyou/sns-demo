package com.sera.snsdemo.domain.member.service;

import com.sera.snsdemo.domain.member.dto.RegisterMemberCommand;
import com.sera.snsdemo.domain.member.entity.Member;
import com.sera.snsdemo.domain.member.entity.MemberNicknameHistory;
import com.sera.snsdemo.domain.member.repository.MemberNicknameHistoryRepository;
import com.sera.snsdemo.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberWriteService {
    final private MemberRepository memberRepository;
    final private MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public Member register(RegisterMemberCommand command) {
        var member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();
        memberRepository.save(member);
        memberNicknameHistoryRepository.save(new MemberNicknameHistory(member));
        return member;
    }

    public void changeNickname(Long id, String nickname) {
        var member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("해당 멤버(%d)가 존재하지 않습니다.", id)));
        member.changeNickname(nickname);
        memberRepository.save(member);
        memberNicknameHistoryRepository.save(new MemberNicknameHistory(member));
    }
}
