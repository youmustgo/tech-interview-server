package me.codinginterview.techinterviewserver.presentation.handler;

import lombok.RequiredArgsConstructor;
import me.codinginterview.techinterviewserver.infra.entity.Comment;
import me.codinginterview.techinterviewserver.domain.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    List<Comment> getComments(@RequestParam String orderBy, @RequestParam boolean descending) {
        return commentService.getComments(orderBy, descending);
    }
}
