package me.codinginterview.techinterviewserver.infra.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @ManyToMany(mappedBy = "bookmarks")
    List<User> bookmarkedUsers;
}
