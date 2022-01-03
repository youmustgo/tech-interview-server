package me.codinginterview.techinterviewserver.domain;

import lombok.AllArgsConstructor;
import me.codinginterview.techinterviewserver.infra.entity.Post;
import me.codinginterview.techinterviewserver.infra.entity.PostRepository;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
public class PostFetcher {
    private final long defaultLimit;
    private final long maximumLimit;
    private final PostRepository postRepository;

    public List<Post> getPosts(String orderBy, String value, boolean descending, long limit) {
        boolean isLimitOutOfRange = (limit <= 0) || (limit >= maximumLimit);
        if (isLimitOutOfRange) {
            limit = defaultLimit;
        }
        switch (orderBy.toLowerCase()) {
            case "date":
                if (descending) {
                    return postRepository.findByCreatedLessThanEqualOrderByCreatedDesc(Date.valueOf(value), limit);
                } else {
                    return postRepository.findByCreatedGreaterThanOrderByCreatedAsc(Date.valueOf(value), limit);
                }
            case "comment":
                if (descending) {
                    return postRepository.findByCommentCountLessThanEqualOrderByCommentCountDesc(Integer.parseInt(value), limit);
                } else {
                    return postRepository.findByCommentCountGreaterThanEqualOrderByCommentCountAsc(Integer.parseInt(value), limit);
                }
            default:
                throw new IllegalArgumentException("order by is empty");
        }
    }
}
