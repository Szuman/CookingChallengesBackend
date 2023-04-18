package com.cookingchallenges.domain.comment.dto;

import com.cookingchallenges.domain.content.dto.ContentDTO;
import com.cookingchallenges.domain.user.dto.UserDTO;

public record CommentDTO(Long id, String text, UserDTO userDTO, ContentDTO contentDTO) {
}
