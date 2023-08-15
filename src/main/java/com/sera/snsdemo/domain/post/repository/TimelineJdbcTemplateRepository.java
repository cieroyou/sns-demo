package com.sera.snsdemo.domain.post.repository;

import com.sera.snsdemo.domain.post.entity.Timeline;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TimelineJdbcTemplateRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    static final String TABLE = "timeline";

    public void bulkInsert(List<Timeline> timelines) {
        var sql = String.format("""
                INSERT INTO `%s` (post_id, member_id, created_at)
                VALUES (:postId, :memberId, :createdAt)
                """, TABLE
        );
        SqlParameterSource[] params = timelines
                .stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(SqlParameterSource[]::new);
        namedParameterJdbcTemplate.batchUpdate(sql, params);
    }
}

