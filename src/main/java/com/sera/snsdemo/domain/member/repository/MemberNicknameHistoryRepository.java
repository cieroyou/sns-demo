package com.sera.snsdemo.domain.member.repository;

import com.sera.snsdemo.domain.member.entity.MemberNicknameHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberNicknameHistoryRepository extends JpaRepository<MemberNicknameHistory, Long> {
    Optional<MemberNicknameHistory> findById(Long id);

    List<MemberNicknameHistory> findAllByMemberId(Long memberId);
}

