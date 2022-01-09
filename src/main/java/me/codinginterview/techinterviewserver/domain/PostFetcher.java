package me.codinginterview.techinterviewserver.domain;

import lombok.AllArgsConstructor;
import me.codinginterview.techinterviewserver.infra.entity.Post;
import me.codinginterview.techinterviewserver.infra.entity.PostRepository;
import org.springframework.data.domain.PageRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class PostFetcher {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final int defaultLimit;
    private final int maximumLimit;
    private final PostRepository postRepository;

    public List<Post> getPosts(String orderBy, String value, boolean descending, int limit) {
        boolean isLimitOutOfRange = (limit <= 0) || (limit >= maximumLimit);
        if (isLimitOutOfRange) {
            limit = defaultLimit;
        }
        switch (orderBy.toLowerCase()) {
            case "date":
                if (descending) {
                    return postRepository.findByCreatedLessThanEqualOrderByCreatedDesc(parseDate(value), PageRequest.of(0, limit));
                } else {
                    return postRepository.findByCreatedGreaterThanOrderByCreatedAsc(parseDate(value), PageRequest.of(0, limit));
                }
            case "comment":
                if (descending) {
                    return postRepository.findByCommentCountLessThanEqualOrderByCommentCountDesc(Integer.parseInt(value), PageRequest.of(0, limit));
                } else {
                    return postRepository.findByCommentCountGreaterThanEqualOrderByCommentCountAsc(Integer.parseInt(value), PageRequest.of(0, limit));
                }
            default:
                throw new IllegalArgumentException("order by is empty");
        }
    }

    private Date parseDate(String dateValue) {
        try {
            return DATE_FORMAT.parse(dateValue);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
