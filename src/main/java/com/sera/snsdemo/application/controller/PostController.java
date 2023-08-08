package com.sera.snsdemo.application.controller;

import com.sera.snsdemo.domain.post.dto.DailyPostCount;
import com.sera.snsdemo.domain.post.dto.DailyPostCountRequest;
import com.sera.snsdemo.domain.post.dto.PostCommand;
import com.sera.snsdemo.domain.post.service.PostReadService;
import com.sera.snsdemo.domain.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostWriteService postWriteService;
    private final PostReadService postReadService;

    @PostMapping()
    public Long register(@RequestBody PostCommand command) {
        return postWriteService.create(command);
    }

    @GetMapping("/daily-post-count")
    public List<DailyPostCount> getDailyPostCount(@RequestBody DailyPostCountRequest dailyPostCountRequest) {
        return postReadService.getDailyPostCount(dailyPostCountRequest);
    }

}
