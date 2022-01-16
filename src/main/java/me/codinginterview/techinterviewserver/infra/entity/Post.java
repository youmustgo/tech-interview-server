package me.codinginterview.techinterviewserver.infra.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
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
}
