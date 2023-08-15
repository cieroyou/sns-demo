package com.sera.snsdemo.domain.follow.service;

import com.sera.snsdemo.domain.follow.dto.FollowDto;
import com.sera.snsdemo.domain.follow.entity.Follow;
import com.sera.snsdemo.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowReadService {
    private final FollowRepository followRepository;

    /**
     * 멤버가 팔로잉 하는 목록
     */
    public List<FollowDto> getFollowings(Long fromMemberId) {
        List<Follow> follows = followRepository.findAllByFromMemberId(fromMemberId);
        return follows.stream()
                .map(follow -> new FollowDto(follow.getFromMemberId(), follow.getToMemberId()))
                .toList();
    }

    public List<FollowDto> getFollowers(Long toMemberId) {
        List<Follow> follows = followRepository.findAllByToMemberId(toMemberId);
        return follows.stream()
                .map(follow -> new FollowDto(follow.getFromMemberId(), follow.getToMemberId()))
                .toList();
    }
}
