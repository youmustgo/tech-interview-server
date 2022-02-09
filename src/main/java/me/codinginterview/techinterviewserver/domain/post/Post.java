package me.codinginterview.techinterviewserver.domain.post;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Post {
    private final Long id;
    private final String title;
    private final String body;
    private final Date created;
    private final long commentCount;
}
