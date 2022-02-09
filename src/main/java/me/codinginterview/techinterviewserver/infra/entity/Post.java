package me.codinginterview.techinterviewserver.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(indexes = {
        @Index(columnList = "created"),
        @Index(columnList = "commentCount")
})
public class Post {
    @Id
    @GeneratedValue
    Long id;
    String title;
    @Lob
    String body;
    Date created;
    @ManyToOne
    User owner;
    long commentCount;

    public Post(Long id) {
        this.id = id;
    }

    public static Post fromDomain(me.codinginterview.techinterviewserver.domain.post.Post post) {
        return Post.builder()
                   .id(post.getId())
                   .title(post.getTitle())
                   .body(post.getBody())
                   .created(post.getCreated())
                   .commentCount(post.getCommentCount())
                   .build();
    }

    public me.codinginterview.techinterviewserver.domain.post.Post toDomain() {
        return me.codinginterview.techinterviewserver.domain.post.Post.builder()
                                                                      .id(id)
                                                                      .title(title)
                                                                      .body(body)
                                                                      .created(created)
                                                                      .commentCount(commentCount)
                                                                      .build();
    }
}
