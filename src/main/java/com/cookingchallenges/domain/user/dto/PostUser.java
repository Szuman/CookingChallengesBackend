package com.cookingchallenges.domain.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public record PostUser(@Size(min = 3, max = 48, message = "Title must have between 3 and 48 characters") String name,
                       @Email(message = "Invalid email value") String email,
                       String password,
                       @Size(max = 1024, message = "Too long about field (max 1024 characters)") String about) {
}
