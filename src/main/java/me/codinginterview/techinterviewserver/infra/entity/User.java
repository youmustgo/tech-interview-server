package me.codinginterview.techinterviewserver.infra.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    Long id;
    @ManyToMany
    List<Post> bookmarks;
    @ManyToMany
    List<Comment> likes;
}
