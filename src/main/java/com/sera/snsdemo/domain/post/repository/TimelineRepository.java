package com.sera.snsdemo.domain.post.repository;

import com.sera.snsdemo.domain.post.entity.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimelineRepository extends JpaRepository<Timeline, Long> {
    List<Timeline> findTop10ByMemberIdOrderByIdDesc(Long memberId);

    // WHERE memberId = {memberId} And id < {id}
    // ORDER BY id desc LIMIT {size}
     // todo: size 를 동적으로, jpql 로 변경하기
    List<Timeline> findTop10ByIdLessThanAndMemberIdOrderByIdDesc(Long id, Long memberId);
}

