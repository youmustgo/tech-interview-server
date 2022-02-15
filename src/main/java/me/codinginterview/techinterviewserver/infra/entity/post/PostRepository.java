package me.codinginterview.techinterviewserver.infra.entity.post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCreatedLessThanEqualOrderByCreatedDesc(Date created, Pageable pageable);
    List<Post> findByCreatedGreaterThanOrderByCreatedAsc(Date created, Pageable pageable);
    List<Post> findByCommentCountLessThanEqualOrderByCommentCountDesc(long commentCount, Pageable pageable);
    List<Post> findByCommentCountGreaterThanEqualOrderByCommentCountAsc(long commentCount, Pageable pageable);

    @Modifying
    @Query("UPDATE Post p SET p.title = ?2, p.body = ?3 WHERE p.id = ?1")
    int updateTitleAndBody(long id, String title, String body);

    @Modifying
    @Query("DELETE FROM Post p WHERE p.id = ?1")
    int deleteById(long id);
}
