package me.codinginterview.techinterviewserver.infra.entity;

import lombok.Getter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Getter
@Entity
@Table(indexes = {
        @Index(columnList = "created")
})
public class Comment {
    @Id
    @GeneratedValue
    Long id;
    Date created;
    @ManyToOne
    Post post;
    // NOTE - only for counting
    @ManyToMany(mappedBy = "likes")
    @OrderColumn
    @LazyCollection(LazyCollectionOption.EXTRA)
    List<User> likedUsers;
}
