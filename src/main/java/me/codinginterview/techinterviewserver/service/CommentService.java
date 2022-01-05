package me.codinginterview.techinterviewserver.service;

import lombok.RequiredArgsConstructor;
import me.codinginterview.techinterviewserver.infra.entity.Comment;
import me.codinginterview.techinterviewserver.respository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> getComments(String orderBy, boolean descending) {
        Comparator<Comment> sort;
        switch (orderBy) {
            case "date":
                sort = Comparator.comparing(comment -> comment.getCreated());
                break;
            case "like":
                sort = Comparator.comparing(comment -> comment.getLikedUsers().size());
                break;
            default:
                throw new IllegalArgumentException("OrderBy cannot be null");
        }
        if (descending) {
            sort = sort.reversed();
        }
        List<Comment> comments = commentRepository.findAll();

        return comments.stream().sorted(sort).collect(Collectors.toList());
    }
}
