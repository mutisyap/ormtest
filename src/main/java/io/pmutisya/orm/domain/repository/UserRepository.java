package io.pmutisya.orm.domain.repository;

import io.pmutisya.orm.domain.User;

public interface UserRepository {
    void save(User user) throws Exception;

    User findById(Long id) throws Exception;
}
