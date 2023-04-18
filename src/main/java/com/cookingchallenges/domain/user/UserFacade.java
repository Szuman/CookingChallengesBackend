package com.cookingchallenges.domain.user;

import com.cookingchallenges.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserFacade {

    private final UserDAO userDAO;

    public User getUserById(Long id) {
        return userDAO.findById(id).orElseThrow(() ->
                new UserNotFoundException("User (id=" + id +") does not exist"));
    }
}
