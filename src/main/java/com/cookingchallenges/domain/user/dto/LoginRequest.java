package com.cookingchallenges.domain.user.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record LoginRequest (@Pattern(regexp = "^[a-zA-Z0-9 _\\\\.@]+$", message = "Invalid pattern surname value - Email must contain @") String email,
                            String password){
}
