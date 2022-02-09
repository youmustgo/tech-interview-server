package me.codinginterview.techinterviewserver.infra.entity;

import lombok.AllArgsConstructor;
import me.codinginterview.techinterviewserver.domain.user.User;
import me.codinginterview.techinterviewserver.domain.user.UserRepository;

import java.util.Optional;

@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final me.codinginterview.techinterviewserver.infra.entity.UserRepository repository;

    @Override
    public Optional<User> find(String namespace, String localId) {
        return repository.findByNamespaceAndLocalId(namespace, localId)
                         .map(user -> user.toDomain());
    }

    @Override
    public User register(User user) {
        return repository.save(me.codinginterview.techinterviewserver.infra.entity.User.fromDomain(user))
                         .toDomain();
    }
}
