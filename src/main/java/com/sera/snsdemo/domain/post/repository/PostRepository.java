package com.sera.snsdemo.domain.post.repository;

import com.sera.snsdemo.domain.post.dto.DailyPostCount;
import com.sera.snsdemo.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    /**
     * DailyPostCount 가 record 일 때
     * "SELECT new com.sera.snsdemo.domain.post.dto.DailyPostCount("
     * + "p.memberId as memberId, p.createdDate as date, count(p.id) as postCount) "
     * + "FROM Post p "
     * + "WHERE memberId = :memberId AND createdDate BETWEEN :firstDate AND :lastDate "
     * + "GROUP BY p.memberId, p.createdDate"
     * DailyPostCount 가 interface 일 때
     * "SELECT "
     * + "p.memberId as memberId, p.createdDate as date, count(p.id) as postCount "
     * + "FROM Post p "
     * + "WHERE memberId = :memberId AND createdDate BETWEEN :firstDate AND :lastDate "
     * + "GROUP BY p.memberId, p.createdDate"
     */

//    value = "SELECT new com.sera.snsdemo.domain.post.dto.DailyPostCount("
//            + "p.memberId as memberId, p.createdDate as date, count(p.id) as postCount) "
//            + "FROM Post p "
//            + "WHERE memberId = :memberId AND createdDate BETWEEN :firstDate AND :lastDate "
//            + "GROUP BY p.memberId, p.createdDate"
    @Query(
            value = "SELECT new com.sera.snsdemo.domain.post.dto.DailyPostCount("
                    + "p.memberId as memberId, p.createdDate as date, count(p.id) as postCount) "
                    + "FROM Post p "
                    + "WHERE memberId = :memberId AND createdDate BETWEEN :firstDate AND :lastDate "
                    + "GROUP BY p.memberId, p.createdDate"
            
            , nativeQuery = false)
    List<DailyPostCount> groupByCreateDate(Long memberId, LocalDate firstDate, LocalDate lastDate);
}

