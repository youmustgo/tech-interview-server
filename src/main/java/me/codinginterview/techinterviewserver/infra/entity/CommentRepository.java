package me.codinginterview.techinterviewserver.infra.entity;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @EntityGraph(attributePaths = {"post", "likedUsers"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Comment> findAll();
}
