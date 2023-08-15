package com.sera.snsdemo.domain.post.service;

import com.sera.snsdemo.domain.post.entity.Timeline;
import com.sera.snsdemo.domain.post.repository.TimelineRepository;
import com.sera.snsdemo.util.CursorRequest;
import com.sera.snsdemo.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimelineReadService {
    private final TimelineRepository timelineRepository;


    public PageCursor<Timeline> getTimelines(Long memberId, CursorRequest cursorRequest) {
        var timelines = findAllBy(memberId, cursorRequest);
        var nextKey = getNextKey(timelines);
        return new PageCursor<>(cursorRequest.next(nextKey), timelines);
    }

    private List<Timeline> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return timelineRepository.findTop10ByIdLessThanAndMemberIdOrderByIdDesc(cursorRequest.key(), memberId);
        }
        return timelineRepository.findTop10ByMemberIdOrderByIdDesc(memberId);
    }

    private long getNextKey(List<Timeline> timelines) {
        return timelines.stream().mapToLong(Timeline::getId).min().orElse(
                // 리스트가 없는 경우
                CursorRequest.NONE_KEY);
    }
}
