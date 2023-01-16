package com.cookingchallenges.domain.user.dto;

public record UserWithPassDTO(Long id, String name, String email, String password, String rank, String about) {
}
