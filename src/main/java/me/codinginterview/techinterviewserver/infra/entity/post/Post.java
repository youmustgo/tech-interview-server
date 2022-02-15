package me.codinginterview.techinterviewserver.infra.entity.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.codinginterview.techinterviewserver.domain.post.PostDto;
import me.codinginterview.techinterviewserver.infra.entity.user.User;

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

    public static Post fromDomain(PostDto post) {
        return Post.builder()
                   .id(post.getId())
                   .title(post.getTitle())
                   .body(post.getBody())
                   .created(post.getCreated())
                   .commentCount(post.getCommentCount())
                   .build();
    }

    public PostDto toDomain() {
        return PostDto.builder()
                      .id(id)
                      .title(title)
                      .body(body)
                      .created(created)
                      .commentCount(commentCount)
                      .build();
    }
}
