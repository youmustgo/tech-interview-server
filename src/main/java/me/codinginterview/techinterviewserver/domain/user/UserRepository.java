package me.codinginterview.techinterviewserver.domain.user;

import java.util.Optional;

public interface UserRepository {
    Optional<User> find(String namespace, String localId);
    User register(User user);
}
