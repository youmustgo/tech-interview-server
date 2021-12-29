package me.codinginterview.techinterviewserver.infra.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    Long id;
    @ManyToOne
    Post post;
    // NOTE - only for counting
    @ManyToMany(mappedBy = "likes")
    @OrderColumn
    @LazyCollection(LazyCollectionOption.EXTRA)
    List<User> likedUsers;
}
