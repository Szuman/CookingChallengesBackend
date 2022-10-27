package com.cookingchallenges.domain.user.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// TODO:    pattern with @ (for email)

public record PostUser(@Size(min = 3, max = 48, message = "Title must have between 3 and 48 characters") String name,
                       @Pattern(regexp = "(^\\p{Lu}\\p{Ll}*$)|(^\\p{Lu}\\p{Ll}{2,}-\\p{Lu}\\p{Ll}*$)",
                               message = "Invalid pattern surname value - Email must contain @") String email,
                       @Size(max = 1024, message = "Too long about field (max 1024 characters)") String about) {
}
