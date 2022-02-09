package me.codinginterview.techinterviewserver.infra.entity.comment;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @EntityGraph(attributePaths = {"owner", "likedUsers"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Comment> findByPostId(Long postId);
}
