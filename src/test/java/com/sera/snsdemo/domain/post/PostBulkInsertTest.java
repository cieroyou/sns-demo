package com.sera.snsdemo.domain.post;

import com.sera.snsdemo.domain.post.entity.Post;
import com.sera.snsdemo.domain.post.repository.PostJdbcTemplateRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PostBulkInsertTest {

    @Autowired
    PostJdbcTemplateRepository postRepository;

    @Test
    public void insertTest() {

        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < 10000 * 100; i++) {
            var post = Post.builder()
                    .memberId(2L)
                    .contents(RandomStringUtils.randomAlphanumeric(10))
                    .createdDate(LocalDate.of((int) (Math.random() * 42) + 1980
                            , (int) (Math.random() * 11) + 1
                            , (int) (Math.random() * 28) + 1))
                    .build();
            posts.add(post);
        }
        postRepository.bulkInsert(posts);
    }

}
