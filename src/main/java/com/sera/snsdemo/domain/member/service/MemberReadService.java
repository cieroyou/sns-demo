package com.sera.snsdemo.domain.member.service;

import com.sera.snsdemo.domain.member.dto.MemberDto;
import com.sera.snsdemo.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberReadService {
    final private MemberRepository memberRepository;

    public MemberDto get(long id) {
        var member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("해당 멤버(%d)가 존재하지 않습니다.", id)));
        return new MemberDto(member);
    }

}
