package me.codinginterview.techinterviewserver.infra.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue
    Long id;
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
}
