package me.codinginterview.techinterviewserver.domain;

import me.codinginterview.techinterviewserver.domain.user.UserRegisterService;
import me.codinginterview.techinterviewserver.domain.user.UserRepository;
import me.codinginterview.techinterviewserver.infra.entity.post.PostRepository;
import me.codinginterview.techinterviewserver.domain.post.PostFetcher;
import me.codinginterview.techinterviewserver.domain.post.PostInserter;
import me.codinginterview.techinterviewserver.domain.post.PostUpdater;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {
    @Bean
    public PostFetcher postFetcher(PostRepository postRepository) {
        return new PostFetcher(10, 100, postRepository);
    }

    @Bean
    public UserRegisterService userService(UserRepository domainUserRepository) {
        return new UserRegisterService(domainUserRepository);
    }

    @Bean
    public PostInserter postInserter(me.codinginterview.techinterviewserver.domain.post.PostRepository postRepository) {
        return new PostInserter(postRepository);
    }

    @Bean
    public PostUpdater postUpdater(me.codinginterview.techinterviewserver.domain.post.PostRepository postRepository) {
        return new PostUpdater(postRepository);
    }
}
