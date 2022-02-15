package me.codinginterview.techinterviewserver.presentation.handler;

import me.codinginterview.techinterviewserver.domain.post.PostFetcher;
import me.codinginterview.techinterviewserver.domain.post.PostInserter;
import me.codinginterview.techinterviewserver.domain.post.PostUpdater;
import me.codinginterview.techinterviewserver.infra.entity.post.Post;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostFetcher postFetcher;
    private final PostInserter postInserter;
    private final PostUpdater postUpdater;

    public PostController(PostFetcher postFetcher, PostInserter postInserter, PostUpdater postUpdater) {
        this.postFetcher = postFetcher;
        this.postInserter = postInserter;
        this.postUpdater = postUpdater;
    }

    @GetMapping
    public List<Post> getPosts(@RequestParam String orderBy,
                               @RequestParam String value,
                               @RequestParam boolean descending,
                               @RequestParam int limit) {
        return postFetcher.getPosts(orderBy, value, descending, limit);
    }

    @PostMapping
    public me.codinginterview.techinterviewserver.domain.post.PostDto insert(@RequestBody me.codinginterview.techinterviewserver.domain.post.PostDto post) {
        return postInserter.insert(post.getTitle(), post.getBody());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody me.codinginterview.techinterviewserver.domain.post.PostDto post) {
        postUpdater.update(id, post.getTitle(), post.getBody());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        postUpdater.delete(id);
    }
}
