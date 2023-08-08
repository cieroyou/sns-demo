package com.sera.snsdemo.application.controller;

import com.sera.snsdemo.domain.post.dto.PostCommand;
import com.sera.snsdemo.domain.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostWriteService postWriteService;

    @PostMapping()
    public Long register(@RequestBody PostCommand command) {
        return postWriteService.create(command);
    }

}
