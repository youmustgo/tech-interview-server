package me.codinginterview.techinterviewserver.infra.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM POST WHERE CREATED <= ?1 ORDER BY CREATED DESC LIMIT ?2", nativeQuery = true)
    List<Post> findByCreatedLessThanEqualOrderByCreatedDesc(Date created, long limit);
    @Query(value = "SELECT * FROM POST WHERE CREATED >= ?1 ORDER BY CREATED ASC LIMIT ?2", nativeQuery = true)
    List<Post> findByCreatedGreaterThanOrderByCreatedAsc(Date created, long limit);
    @Query(value = "SELECT * FROM POST WHERE COMMENT_COUNT <= ?1 ORDER BY COMMENT_COUNT ASC LIMIT ?2", nativeQuery = true)
    List<Post> findByCommentCountLessThanEqualOrderByCommentCountDesc(long commentCount, long limit);
    @Query(value = "SELECT * FROM POST WHERE COMMENT_COUNT >= ?1 ORDER BY COMMENT_COUNT ASC LIMIT ?2", nativeQuery = true)
    List<Post> findByCommentCountGreaterThanEqualOrderByCommentCountAsc(long commentCount, long limit);
}
