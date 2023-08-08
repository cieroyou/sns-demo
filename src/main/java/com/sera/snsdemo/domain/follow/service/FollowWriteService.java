package com.sera.snsdemo.domain.follow.service;

import com.sera.snsdemo.domain.follow.entity.Follow;
import com.sera.snsdemo.domain.follow.repository.FollowRepository;
import com.sera.snsdemo.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class FollowWriteService {

    private final FollowRepository followRepository;

    /**
     * 요구사항
     * fromMember 와 toMember 는 달라야 한다.
     *
     * @param fromMember
     * @param toMember
     */
    public void create(MemberDto fromMember, MemberDto toMember) {
        Assert.isTrue(!fromMember.getId().equals(toMember.getId()), "fromMember 와 toMember 는 달라야 합니다");
        var follow = Follow.builder()
                .fromMemberId(fromMember.getId())
                .toMemberId(toMember.getId()).build();
        followRepository.save(follow);
    }
}
