package me.codinginterview.techinterviewserver.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class UserDto {
    private final Long id;
    private final String namespace;
    private final String localId;
    private final String name;
    private final String session;
}
