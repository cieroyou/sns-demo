package com.sera.snsdemo.domain.post.service;

import com.sera.snsdemo.domain.post.entity.Timeline;
import com.sera.snsdemo.domain.post.repository.TimelineJdbcTemplateRepository;
import com.sera.snsdemo.domain.post.repository.TimelineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimelineWriteService {
    private final TimelineRepository timelineRepository;
    private final TimelineJdbcTemplateRepository timelineJdbcTemplateRepository;

    /**
     * 특정 멤버를 팔로잉하는 멤버들(memberids)에게 게시글을 배달할 Timeline Table 에 row Insert
     *
     * @param postId    특정멤버가 작성한 게시글 Id
     * @param memberIds 게시글(postId)를 작성한 멤버를 팔로이하는 멤버들의 Id 목록
     * @return
     */
    public void deliveryTimeline(Long postId, List<Long> memberIds) {
        var timelines = memberIds.stream()
                .map(memberId -> toTimeline(postId, memberId))
                .toList();
        timelineJdbcTemplateRepository.bulkInsert(timelines);
    }

    private Timeline toTimeline(Long postId, Long memberId) {
        return Timeline.builder().postId(postId).memberId(memberId).build();
    }
}
