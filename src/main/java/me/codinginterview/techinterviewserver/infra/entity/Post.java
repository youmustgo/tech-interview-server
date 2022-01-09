package me.codinginterview.techinterviewserver.infra.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

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
}
