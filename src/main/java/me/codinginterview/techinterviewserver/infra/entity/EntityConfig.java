package me.codinginterview.techinterviewserver.infra.entity;

import me.codinginterview.techinterviewserver.domain.user.UserRepository;
import me.codinginterview.techinterviewserver.domain.post.PostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntityConfig {
    @Bean
    public UserRepository domainUserRepository(me.codinginterview.techinterviewserver.infra.entity.UserRepository userRepository) {
        return new UserRepositoryAdapter(userRepository);
    }

    @Bean
    public PostRepository postRepositoryAdapter(me.codinginterview.techinterviewserver.infra.entity.PostRepository entityRepository) {
        return new PostRepositoryAdapter(entityRepository);
    }
}
