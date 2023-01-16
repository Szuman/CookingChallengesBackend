package com.cookingchallenges.mappers;

import com.cookingchallenges.domain.user.User;
import com.cookingchallenges.domain.user.dto.UserDTO;
import com.cookingchallenges.domain.user.dto.UserWithPassDTO;

public class UserMapper {

    private UserMapper() {
    }

    public static UserDTO map(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getGrade().toString(),
                user.getAbout()
        );
    }

    public static UserWithPassDTO mapPass(User user) {
        return new UserWithPassDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getGrade().toString(),
                user.getAbout()
        );
    }
}

