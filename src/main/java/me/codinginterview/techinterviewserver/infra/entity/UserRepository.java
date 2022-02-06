package me.codinginterview.techinterviewserver.infra.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNamespaceAndLocalId(String namespace, String localId);
}
