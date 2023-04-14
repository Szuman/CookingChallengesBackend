package com.cookingchallenges.domain.user.dto;

import com.cookingchallenges.domain.user.Grade;
public record SignupResponse(String accessToken, Long id, String username, String email, Grade grade) {
}
