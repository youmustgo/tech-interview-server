package me.codinginterview.techinterviewserver.infra.entity.user;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import me.codinginterview.techinterviewserver.domain.user.UserDto;
import me.codinginterview.techinterviewserver.infra.entity.collage.Collage;
import me.codinginterview.techinterviewserver.infra.entity.collage.Role;
import me.codinginterview.techinterviewserver.infra.entity.collage.RoleConverter;
import me.codinginterview.techinterviewserver.infra.entity.comment.Comment;
import me.codinginterview.techinterviewserver.infra.entity.post.Post;

import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@Entity
@Table(indexes = {
        @Index(columnList = "namespace,localId"),
        @Index(columnList = "session")
})
public class User {
    @Id
    @GeneratedValue
    Long id;
    String namespace;
    String localId;
    String name;
    String session;
    @ManyToMany
    List<Post> bookmarks;
    @ManyToMany
    List<Comment> likes;
    @Convert(converter = RoleConverter.class)
    Role role;
    @ManyToMany
    List<Collage> collageBookmarks;

    public User(Long id) {
        this.id = id;
    }

    public static User fromDomain(UserDto user) {
        return User.builder()
                   .id(user.getId())
                   .namespace(user.getNamespace())
                   .localId(user.getLocalId())
                   .name(user.getName())
                   .session(user.getSession())
                   .build();
    }

    public UserDto toDomain() {
        return UserDto.builder()
                      .id(id)
                      .namespace(namespace)
                      .localId(localId)
                      .name(name)
                      .session(session)
                      .build();
    }
}
