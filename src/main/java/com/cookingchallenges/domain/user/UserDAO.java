package com.cookingchallenges.domain.user;

import java.util.Optional;

public interface UserDAO {
    Long save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
    void deleteById(Long id);
    Optional<User> findByEmail(String email);
}
