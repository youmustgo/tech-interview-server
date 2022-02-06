package me.codinginterview.techinterviewserver.infra.entity;

import me.codinginterview.techinterviewserver.domain.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntityConfig {
    @Bean
    public UserRepository domainUserRepository(me.codinginterview.techinterviewserver.infra.entity.UserRepository userRepository) {
        return new UserRepositoryAdapter(userRepository);
    }
}
