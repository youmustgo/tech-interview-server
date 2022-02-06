package me.codinginterview.techinterviewserver.domain.post;

import java.util.Optional;

public interface PostRepository {
    Post insert(Post post);
    boolean updateTitleAndBody(long id, String title, String body);
    boolean delete(long id);
}
