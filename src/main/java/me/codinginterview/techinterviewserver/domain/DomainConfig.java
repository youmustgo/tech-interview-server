package me.codinginterview.techinterviewserver.domain;

import me.codinginterview.techinterviewserver.infra.entity.PostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {
    @Bean
    public PostFetcher postFetcher(PostRepository postRepository) {
        return new PostFetcher(10, 100, postRepository);
    }
}