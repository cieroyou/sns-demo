package com.sera.snsdemo.domain.post.service;

import com.sera.snsdemo.domain.post.dto.DailyPostCount;
import com.sera.snsdemo.domain.post.dto.DailyPostCountRequest;
import com.sera.snsdemo.domain.post.entity.Post;
import com.sera.snsdemo.domain.post.repository.PostRepository;
import com.sera.snsdemo.util.CursorRequest;
import com.sera.snsdemo.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<Post> getPosts(Long memberId, PageRequest pageRequest) {
        return postRepository.findAllByMemberId(memberId, pageRequest);
    }

    // id를 cursor key 로 사용
    // 1) 키가 있을 때
    // SELECT * FROM post WHERE memberId = 4 AND id > 100
    // 2) 키가 없을 때(key = null)
    // SELECT * FROM post WHERE memberId = 4
    public PageCursor<Post> getPosts(Long memberId, CursorRequest cursorRequest) {
        var posts = findAllBy(memberId, cursorRequest);
        var nextKey = posts.stream()
                .mapToLong(Post::getId).min().orElse(
                        // 리스트가 없는 경우
                        CursorRequest.NONE_KEY
                );
        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    private List<Post> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return postRepository.findTop10ByIdLessThanAndMemberIdOrderByIdDesc(cursorRequest.key(), memberId);
        }
        return postRepository.findTop10ByMemberIdOrderByIdDesc(memberId);
    }
}
