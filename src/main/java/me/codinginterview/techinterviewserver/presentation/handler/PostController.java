package me.codinginterview.techinterviewserver.presentation.handler;

import me.codinginterview.techinterviewserver.domain.PostFetcher;
import me.codinginterview.techinterviewserver.infra.entity.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostFetcher postFetcher;

    public PostController(PostFetcher postFetcher) {
        this.postFetcher = postFetcher;
    }

    @GetMapping
    public List<Post> getPosts(@RequestParam String orderBy,
                               @RequestParam String value,
                               @RequestParam boolean descending,
                               @RequestParam long limit) {
        return postFetcher.getPosts(orderBy, value, descending, limit);
    }
}
