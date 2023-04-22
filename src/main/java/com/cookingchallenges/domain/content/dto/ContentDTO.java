package com.cookingchallenges.domain.content.dto;

import com.cookingchallenges.domain.user.dto.UserDTO;

import java.util.List;

public record ContentDTO(Long id, String title, String type, UserDTO user, String description, List<String> products) {
}
