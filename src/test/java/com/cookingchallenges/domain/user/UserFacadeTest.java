package com.cookingchallenges.domain.user;

import com.cookingchallenges.domain.comment.CommentFacade;
import com.cookingchallenges.domain.content.ContentFacade;
import com.cookingchallenges.domain.user.dto.EditUser;
import com.cookingchallenges.domain.user.dto.PostUser;
import com.cookingchallenges.domain.user.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserFacadeTest {

    @Mock
    UserDAO userDao;

    @Mock
    CommentFacade commentFacade;

    @Mock
    ContentFacade contentFacade;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    UserApiFacade userApiFacade = new UserApiFacade(userDao, commentFacade, contentFacade, passwordEncoder);

    @Test
    void getUserById() {
        //given
        Long id = 1L;
        UserDTO expected = UserFixtures.userDTO(id);

        //when
        UserDTO actual = userApiFacade.getUserById(id);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getUserByName() {
        //given
        String name = "";
        UserDTO expected = UserFixtures.userDTO(name);

        //when
        UserDTO actual = userApiFacade.getUserByName(name);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void postUser() {
        //given
        Long expected = 1L;
        String password = "";
        String encodedPassword = "";
        PostUser postUser = UserFixtures.postUserWithPassword(encodedPassword);
        User user = UserFixtures.user();

        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(userDao.save(user)).thenReturn(expected);

        //when
        Long actual = userApiFacade.postUser(postUser);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void editUser() {
        //given
        Long id = 1L;
        EditUser editUser = UserFixtures.editUser();
        User user = UserFixtures.user();
        //todo: tududu

        //when
        userApiFacade.editUser(editUser, id);

        //then
        verify(userDao).save(user);
    }

    @Test
    void deleteUser() {
        //given
        Long id = 1L;
        User user = UserFixtures.user(id);
        when(userDao.findById(id)).thenReturn(Optional.of(user));

        //when
        userApiFacade.deleteUser(id);

        //then
        verify(commentFacade).deleteAllByCreator(user);
        verify(contentFacade).deleteAllByUser(user);
        verify(userDao).deleteById(id);
    }

    @Test
    void changeUsersRank() {
        //given
        Long id = 1L;
        Grade grade = Grade.BEGINNER;
        User user = UserFixtures.user(id);
        UserDTO expected = UserFixtures.userDTO(id);
        when(userDao.findById(id)).thenReturn(Optional.of(user));

        //when
        UserDTO actual = userApiFacade.changeUsersRank(id, grade);

        //then
        assertThat(actual).isEqualTo(expected);
        verify(userDao).save(user);
    }
}