package me.codinginterview.techinterviewserver.domain.comment;

import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class CreateCommentDto {
    private Long commentId;

    private String body;
}
