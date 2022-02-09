package me.codinginterview.techinterviewserver.infra.entity;

import lombok.AllArgsConstructor;
import me.codinginterview.techinterviewserver.domain.post.Post;
import me.codinginterview.techinterviewserver.domain.post.PostRepository;

@AllArgsConstructor
public class PostRepositoryAdapter implements PostRepository {
    private final me.codinginterview.techinterviewserver.infra.entity.PostRepository postRepository;

    @Override
    public Post insert(Post post) {
        return postRepository.save(me.codinginterview.techinterviewserver.infra.entity.Post.fromDomain(post)).toDomain();
    }

    @Override
    public boolean updateTitleAndBody(long id, String title, String body) {
        return postRepository.updateTitleAndBody(id, title, body) > 0;
    }

    @Override
    public boolean delete(long id) {
        return postRepository.deleteById(id) > 0;
    }
}
