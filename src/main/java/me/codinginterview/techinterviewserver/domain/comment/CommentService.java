package me.codinginterview.techinterviewserver.domain.comment;

import lombok.RequiredArgsConstructor;
import me.codinginterview.techinterviewserver.infra.entity.comment.Comment;
import me.codinginterview.techinterviewserver.infra.entity.comment.CommentAdapter;
import me.codinginterview.techinterviewserver.infra.entity.comment.CommentRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final CommentAdapter commentAdapter;

    public List<Comment> getComments(Long postId, String orderBy, boolean descending) {
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

        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().sorted(sort).collect(Collectors.toList());
    }

    public Comment createComment(Long postId, Long commentId, String body) {
        Long viewerId = Long.valueOf(1);

        if(commentId != null) {
            commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Not Found Parent Comment"));
        }

        return commentAdapter.createComment(viewerId, postId, commentId, body);
    }

    public void updateComment(Long commentId, String body) {
        Long viewerId = Long.valueOf(1);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Not Found Comment"));

        if(viewerId != comment.getOwner().getId()) {
            throw new IllegalArgumentException("Not the owner");
        }

        commentAdapter.updateComment(comment, body);
    }

    public void deleteComment(Long commentId) {
        Long viewerId = Long.valueOf(1);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Not Found Comment"));

        if(viewerId != comment.getOwner().getId() || viewerId != comment.getPost().getOwner().getId()) {
            throw new IllegalArgumentException("Not the owner");
        }

        commentAdapter.deleteComment(comment);
    }
}
