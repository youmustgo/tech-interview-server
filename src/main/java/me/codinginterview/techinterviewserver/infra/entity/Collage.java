package me.codinginterview.techinterviewserver.infra.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "collage")
public class Collage {
    @Id
    @GeneratedValue
    Long id;
    String title;
    @Lob
    String body;
    Boolean opened;
    @ManyToOne
    User owner;
    @ManyToMany
    List<Post> posts;
    @ManyToMany(mappedBy = "collageBookmarks")
    List<User> collageBookmarkUsers;

    @Builder
    public Collage(String title, String body, Boolean opened, Long userId, List<Long> postIds) {
        this.title = title;
        this.body = body;
        this.opened = opened;
        this.owner = new User(userId);
        this.posts = postIds.stream().map(postId -> {
            Post post = new Post(postId);
            return post;
        }).collect(Collectors.toList());
    }
}
