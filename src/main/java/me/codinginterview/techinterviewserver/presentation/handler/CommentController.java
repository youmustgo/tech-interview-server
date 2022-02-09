package me.codinginterview.techinterviewserver.presentation.handler;

import lombok.RequiredArgsConstructor;
import me.codinginterview.techinterviewserver.domain.comment.CreateCommentDto;
import me.codinginterview.techinterviewserver.domain.comment.CommentService;
import me.codinginterview.techinterviewserver.infra.entity.comment.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{postId}")
    List<Comment> getComments(@PathVariable Long postId, @RequestParam String orderBy, @RequestParam boolean descending) {
        return commentService.getComments(postId, orderBy, descending);
    }

    @PostMapping("/{postId}")
    Comment createComment(@PathVariable Long postId, @RequestBody CreateCommentDto createCommentDto) {
        return commentService.createComment(postId, createCommentDto.getCommentId(), createCommentDto.getBody());
    }

    @PutMapping("/{commentId}")
    void updateComment(@PathVariable Long commentId, @RequestBody CreateCommentDto createCommentDto) {
        commentService.updateComment(commentId, createCommentDto.getBody());
    }

    @DeleteMapping("/{commentId}")
    void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
