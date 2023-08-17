package com.sera.snsdemo.application.controller;

import com.sera.snsdemo.application.usecase.CreatePostLikeUsecase;
import com.sera.snsdemo.application.usecase.CreatePostUsecase;
import com.sera.snsdemo.application.usecase.GetTimelinePostsUsecase;
import com.sera.snsdemo.domain.post.dto.DailyPostCount;
import com.sera.snsdemo.domain.post.dto.DailyPostCountRequest;
import com.sera.snsdemo.domain.post.dto.PostCommand;
import com.sera.snsdemo.domain.post.entity.Post;
import com.sera.snsdemo.domain.post.service.PostReadService;
import com.sera.snsdemo.domain.post.service.PostWriteService;
import com.sera.snsdemo.util.CursorRequest;
import com.sera.snsdemo.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostWriteService postWriteService;
    private final PostReadService postReadService;
    private final GetTimelinePostsUsecase getTimelinePostsUsecase;
    private final CreatePostUsecase createPostUsecase;
    private final CreatePostLikeUsecase createPostLikeUsecase;

    @PostMapping()
    public Long register(@RequestBody PostCommand command) {
        return createPostUsecase.execute(command);
    }

    @GetMapping("/daily-post-count")
    public List<DailyPostCount> getDailyPostCount(@RequestBody DailyPostCountRequest dailyPostCountRequest) {
        return postReadService.getDailyPostCount(dailyPostCountRequest);
    }

    @GetMapping("/members/{memberId}")
    public Page<Post> getPosts(@PathVariable Long memberId,
                               @RequestParam Integer page, @RequestParam Integer size) {
        Sort sort = Sort.by("id").descending();
        return postReadService.getPosts(memberId, PageRequest.of(page, size, sort));
    }

    @GetMapping("/members/{memberId}/by-cursor")
    public PageCursor<Post> getPostsByCursor(@PathVariable Long memberId,
                                             @RequestBody CursorRequest cursorRequest) {
        return postReadService.getPosts(memberId, cursorRequest);
    }

    /**
     * member가 팔로잉하는 사람들이 작성한 게시글 목록
     *
     * @param memberId
     * @param cursorRequest
     * @return
     */
    @GetMapping("/members/{memberId}/timeline")
    public PageCursor<Post> getTimeline(@PathVariable Long memberId,
                                        @RequestBody CursorRequest cursorRequest) {
        return getTimelinePostsUsecase.executeByTimeline(memberId, cursorRequest);
    }

    @PostMapping("/{postId}/like/v1")
    public void incrementLike(@PathVariable Long postId) {
        postWriteService.likePost(postId);
    }

    @PostMapping("/{postId}/like/v2")
    public void incrementLikeV2(@PathVariable Long postId, @RequestParam Long memberId) {
        createPostLikeUsecase.execute(postId, memberId);
    }
}
