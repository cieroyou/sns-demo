package com.sera.snsdemo.domain.post.service;

import com.sera.snsdemo.domain.post.dto.DailyPostCount;
import com.sera.snsdemo.domain.post.dto.DailyPostCountRequest;
import com.sera.snsdemo.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReadService {
    private final PostRepository postRepository;

    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {
        return postRepository.groupByCreateDate(
                request.getMemberId(), request.getFirstDate(), request.getLastDate());
    }
}
