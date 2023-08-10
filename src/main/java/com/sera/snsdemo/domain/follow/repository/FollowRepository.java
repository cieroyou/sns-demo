package com.sera.snsdemo.domain.follow.repository;

import com.sera.snsdemo.domain.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByFromMemberId(Long fromMemberId);

    List<Follow> findAllByToMemberId(Long toMemberId);

}
