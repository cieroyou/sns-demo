package com.sera.snsdemo.domain.post.repository;

import com.sera.snsdemo.domain.post.dto.DailyPostCount;
import com.sera.snsdemo.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * SELECT * FROM Posts WHERE memberId = ${memberId } LIMIT ${size}, ${offset}
     *
     * @param memberId
     * @param pageable
     * @return
     */
    Page<Post> findAllByMemberId(Long memberId, Pageable pageable);

    // ORDER BY id desc LIMIT {size}
    // todo: size 를 동적으로, jpql 로 변경하기
    List<Post> findTop10ByMemberIdOrderByIdDesc(Long memberId);

    // ORDER BY id desc LIMIT {size}
    // todo: size 를 동적으로, jpql 로 변경하기
    List<Post> findTop10ByMemberIdInOrderByIdDesc(List<Long> memberIds);


    // WHERE memberId = {memberId} And id < {id}
    // ORDER BY id desc LIMIT {size}
    // todo: size 를 동적으로, jpql 로 변경하기
    List<Post> findTop10ByIdLessThanAndMemberIdOrderByIdDesc(Long id, Long memberId);

    List<Post> findTop10ByIdLessThanAndMemberIdInOrderByIdDesc(Long id, List<Long> memberId);
}

