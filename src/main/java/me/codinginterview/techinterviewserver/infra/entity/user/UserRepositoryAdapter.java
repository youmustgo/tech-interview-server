package me.codinginterview.techinterviewserver.infra.entity.user;

import lombok.AllArgsConstructor;
import me.codinginterview.techinterviewserver.domain.user.UserDto;
import me.codinginterview.techinterviewserver.domain.user.UserRepository;

import java.util.Optional;

@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final me.codinginterview.techinterviewserver.infra.entity.user.UserRepository repository;

    @Override
    public Optional<UserDto> find(String namespace, String localId) {
        return repository.findByNamespaceAndLocalId(namespace, localId)
                         .map(user -> user.toDomain());
    }

    @Override
    public UserDto register(UserDto user) {
        return repository.save(me.codinginterview.techinterviewserver.infra.entity.user.User.fromDomain(user))
                         .toDomain();
    }
}
