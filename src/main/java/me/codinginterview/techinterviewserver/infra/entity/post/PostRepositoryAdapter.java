package me.codinginterview.techinterviewserver.infra.entity.post;

import lombok.AllArgsConstructor;
import me.codinginterview.techinterviewserver.domain.post.PostDto;
import me.codinginterview.techinterviewserver.domain.post.PostRepository;

@AllArgsConstructor
public class PostRepositoryAdapter implements PostRepository {
    private final me.codinginterview.techinterviewserver.infra.entity.post.PostRepository postRepository;

    @Override
    public PostDto insert(PostDto post) {
        return postRepository.save(me.codinginterview.techinterviewserver.infra.entity.post.Post.fromDomain(post)).toDomain();
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
