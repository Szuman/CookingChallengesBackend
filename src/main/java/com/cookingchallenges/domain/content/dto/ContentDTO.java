package com.cookingchallenges.domain.content.dto;

import com.cookingchallenges.domain.comment.dto.CommentDTO;
import com.cookingchallenges.domain.user.dto.UserDTO;
import lombok.Value;

import java.util.List;

public record ContentDTO(Long id, String title, String type, UserDTO user, String description, String products) {
}
