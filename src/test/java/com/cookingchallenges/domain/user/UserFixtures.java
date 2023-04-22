package com.cookingchallenges.domain.user;

import com.cookingchallenges.domain.user.dto.EditUser;
import com.cookingchallenges.domain.user.dto.PostUser;
import com.cookingchallenges.domain.user.dto.UserDTO;

public class UserFixtures {
    private UserFixtures() {}

    public static Long DUMMY_USER_ID = 789L;

    public static User user() {
        return new User(DUMMY_USER_ID, "john_doe", "john_doe@example.com", "Str0ngPassw0rd", Grade.BEGINNER, "started cooking");
    }

    public static User user(Long id) {
        return new User(id, "john_doe", "john_doe@example.com", "Str0ngPassw0rd", Grade.BEGINNER, "started cooking");
    }

    public static UserDTO userDTO() {
        return new UserDTO(DUMMY_USER_ID, "john_doe", "john_doe@example.com", "BEGINNER", "started cooking");
    }

    public static UserDTO userDTO(Long id) {
        return new UserDTO(id, "john_doe", "john_doe@example.com", "BEGINNER", "started cooking");
    }

    public static UserDTO userDTO(String name) {
        return new UserDTO(DUMMY_USER_ID, name, "john_doe@example.com", "BEGINNER", "started cooking");
    }

    public static PostUser postUserWithPassword(String password) {
        return new PostUser("john_doe", "john_doe@example.com", password, "started cooking");
    }

    public static EditUser editUser() {
        return new EditUser("john_doe", "john_doe@example.com", "started cooking");
    }
}
