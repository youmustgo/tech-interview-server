package me.codinginterview.techinterviewserver.infra.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    Long id;
    @ManyToOne
    Post post;
}
