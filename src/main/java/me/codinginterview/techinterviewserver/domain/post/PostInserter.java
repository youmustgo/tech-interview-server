package me.codinginterview.techinterviewserver.domain.post;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostInserter {
    private final PostRepository repository;

    public Post insert(String title, String body) {
        if (StringUtils.isBlank(title)) {
            throw new IllegalArgumentException("title is empty");
        }
        if (StringUtils.isBlank(body)) {
            throw new IllegalArgumentException("body is empty");
        }
        return repository.insert(Post.builder()
                                     .title(title)
                                     .body(body)
                                     .created(new Date())
                                     .commentCount(0)
                                     .build());
    }
}
