package me.codinginterview.techinterviewserver.infra.entity.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CommentAdapter {

    private final CommentRepository commentRepository;

    @Transactional
    public Comment createComment(Long viewerId, Long postId, Long commentId, String body) {
        Comment comment = Comment.builder().body(body).postId(postId).commentId(commentId).ownerId(viewerId).build();
        return commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Comment comment, String body) {
        comment.updateBody(body);
        commentRepository.save(comment);

    }

    @Transactional
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
