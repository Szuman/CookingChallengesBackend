package com.cookingchallenges.domain.user.dto;

import javax.validation.constraints.Email;

public record LoginRequest (@Email(message = "Invalid email value") String email,
                            String password){
}
