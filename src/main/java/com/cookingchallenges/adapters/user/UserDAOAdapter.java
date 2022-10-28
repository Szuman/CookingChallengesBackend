package com.cookingchallenges.adapters.user;

import com.cookingchallenges.domain.user.User;
import com.cookingchallenges.domain.user.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class UserDAOAdapter implements UserDAO {

    private final UserRepository userRepository;

    @Override
    public Long save(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
