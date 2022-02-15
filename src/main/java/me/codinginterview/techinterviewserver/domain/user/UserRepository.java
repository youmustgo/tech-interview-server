package me.codinginterview.techinterviewserver.domain.user;

import java.util.Optional;

public interface UserRepository {
    Optional<UserDto> find(String namespace, String localId);
    UserDto register(UserDto user);
}
