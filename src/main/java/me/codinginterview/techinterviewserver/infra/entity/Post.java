package me.codinginterview.techinterviewserver.infra.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
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
    @OneToMany(mappedBy = "post")
    List<Comment> comments;
    @ManyToMany(mappedBy = "bookmarks")
    List<User> bookmarkedUsers;
}
