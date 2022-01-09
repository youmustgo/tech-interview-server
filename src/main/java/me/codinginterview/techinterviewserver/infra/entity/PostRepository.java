package me.codinginterview.techinterviewserver.infra.entity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCreatedLessThanEqualOrderByCreatedDesc(Date created, Pageable pageable);
    List<Post> findByCreatedGreaterThanOrderByCreatedAsc(Date created, Pageable pageable);
    List<Post> findByCommentCountLessThanEqualOrderByCommentCountDesc(long commentCount, Pageable pageable);
    List<Post> findByCommentCountGreaterThanEqualOrderByCommentCountAsc(long commentCount, Pageable pageable);
}
