package me.codinginterview.techinterviewserver.infra.entity.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.codinginterview.techinterviewserver.infra.entity.Post;
import me.codinginterview.techinterviewserver.infra.entity.User;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(indexes = {
        @Index(columnList = "created")
})
public class Comment {
    @Id
    @GeneratedValue
    Long id;

    @Lob
    String body;

    Date created;

    @ManyToOne
    User owner;

    @JsonIgnore
    @ManyToOne
    Post post;

    @JsonIgnore
    @ManyToOne
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> childComments;

    // NOTE - only for counting
    @ManyToMany(mappedBy = "likes")
    @OrderColumn
    @LazyCollection(LazyCollectionOption.EXTRA)
    List<User> likedUsers;

    @Builder
    public Comment(String body, Long ownerId, Long postId, Long commentId) {
        this.body = body;
        this.created = new Date();
        this.owner = new User(ownerId);
        this.post = new Post(postId);

        if (commentId != null) {
            this.parentComment = new Comment(commentId);
        }
    }

    public Comment(Long id) {
        this.id = id;
    }

    public void updateBody(String body) {
        this.body = body;
    }
}
