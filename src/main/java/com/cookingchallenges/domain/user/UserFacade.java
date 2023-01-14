package com.cookingchallenges.domain.user;

import com.cookingchallenges.domain.comment.CommentDAO;
import com.cookingchallenges.domain.content.ContentDAO;
import com.cookingchallenges.domain.user.dto.EditUser;
import com.cookingchallenges.domain.user.dto.PostUser;
import com.cookingchallenges.domain.user.dto.UserDTO;
import com.cookingchallenges.domain.user.exception.UserNotFoundException;
import com.cookingchallenges.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserFacade implements UserDetailsService {

    private final UserDAO userDao;
    private final CommentDAO commentDAO;
    private final ContentDAO contentDAO;
    private final BCryptPasswordEncoder passwordEncoder;

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

    @Transactional
    public Long postUser(PostUser postUser) {
        User user = new User(postUser.name(), postUser.email(), postUser.about());
        user.setPassword(passwordEncoder.encode(postUser.password()));
        return userDao.save(user);
    }

    @Transactional
    public UserDTO editUser(EditUser editUser, Long id) {
        User user = userDao.findById(id).orElseThrow(() ->
                new UserNotFoundException("User (id=" + id +") does not exist"));
        user.setName(editUser.name());
        user.setEmail(editUser.email());
        user.setAbout(editUser.about());
        userDao.save(user);
        return getUserById(id);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userDao.findById(id).orElseThrow(() ->
                new UserNotFoundException("User (id=" + id +") does not exist"));
        commentDAO.deleteByCreator(user);
        contentDAO.deleteByUser(user);
        userDao.deleteById(id);
    }

    public UserDTO changeUsersRank(Long id, Rank rank) {
        User user = userDao.findById(id).orElseThrow(() ->
                new UserNotFoundException("User (id=" + id +") does not exist"));
        user.setRank(rank);
        userDao.save(user);
        return getUserById(id);
    }

    public User getUserByEmail(String email) {
        return userDao.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("User (email=" + email +") does not exist"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("User (email=" + email +") does not exist"));
    }
}