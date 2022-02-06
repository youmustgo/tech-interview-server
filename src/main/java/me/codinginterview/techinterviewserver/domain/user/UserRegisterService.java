package me.codinginterview.techinterviewserver.domain.user;

import lombok.AllArgsConstructor;
import me.codinginterview.techinterviewserver.domain.DomainException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class UserRegisterService {
    private final UserRepository userRepository;

    @Transactional
    public User register(User user) {
        validateUser(user);
        Long mayBeUserId = userRepository.find(user.getNamespace(), user.getLocalId())
                                         .map(User::getId)
                                         .orElse(null);
        return userRepository.register(user.toBuilder()
                                           .id(mayBeUserId)
                                           .session(RandomStringUtils.randomAlphanumeric(32))
                                           .build());
    }

    private void validateUser(User user) {
        if (StringUtils.isBlank(user.getNamespace())) {
            throw new DomainException.BadRequest("user namespace is empty");
        }
        if (StringUtils.isBlank(user.getLocalId())) {
            throw new DomainException.BadRequest("user id is empty");
        }
        if (StringUtils.isBlank(user.getName())) {
            throw new DomainException.BadRequest("user name is empty");
        }
    }
}
