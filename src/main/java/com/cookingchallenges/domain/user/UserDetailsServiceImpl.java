package com.cookingchallenges.domain.user;

import com.cookingchallenges.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDAO userDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        return userDAO.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("User (email=" + email +") does not exist"));
    }
}
