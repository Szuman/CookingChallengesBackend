package com.cookingchallenges.domain.user.dto;

import lombok.Value;

public record UserDTO(Long id, String name, String email, String rank, String about) {
}
