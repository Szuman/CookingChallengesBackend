package com.cookingchallenges.domain.user;

import com.cookingchallenges.domain.user.dto.EditUser;
import com.cookingchallenges.domain.user.dto.PostUser;
import com.cookingchallenges.domain.user.dto.UserDTO;
import com.cookingchallenges.domain.user.exception.UserNotFoundException;
import com.cookingchallenges.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserDAO userDao;

    public UserDTO getUserById(Long id) {
        User user = userDao.findById(id).orElseThrow(() ->
                new UserNotFoundException("User (id=" + id +") does not exist"));
        return UserMapper.map(user);
    }

    public UserDTO getUserByName(String name) {
        User user = userDao.findByName(name).orElseThrow(() ->
                new UserNotFoundException("User (name=" + name +") does not exist"));
        return UserMapper.map(user);
    }

    public Long postUser(PostUser postUser) {
        return userDao.save(new User(postUser.name(), postUser.email(), postUser.about()));
    }

    public void editUser(EditUser editUser) {
    }
    public void deleteUser(Long id) {
    }

    public void changeUsersRank(Long id, String rank) {
        //TODO: update user's rank
    }
}