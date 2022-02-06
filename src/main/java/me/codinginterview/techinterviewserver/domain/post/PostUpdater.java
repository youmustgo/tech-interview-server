package me.codinginterview.techinterviewserver.domain.post;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.tools.ISupportsMessageContext;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostUpdater {
    private final PostRepository repository;

    @Transactional
    public void update(long id, String title, String body) {
        if (StringUtils.isBlank(title)) {
            throw new IllegalArgumentException("title is empty");
        }
        if (StringUtils.isBlank(body)) {
            throw new IllegalArgumentException("body is empty");
        }
        boolean isSuccess = repository.updateTitleAndBody(id, title, body);
        if (isSuccess == false) {
            throw new IllegalArgumentException("post " + id + " is not found");
        }
    }

    public void delete(long id) {
        boolean isSuccess = repository.delete(id);
        if (isSuccess == false) {
            throw new IllegalArgumentException("post " + id + " is not found");
        }
    }
}
