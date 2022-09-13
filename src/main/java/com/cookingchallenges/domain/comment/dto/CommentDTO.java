package com.cookingchallenges.domain.comment.dto;

import com.cookingchallenges.domain.content.dto.ContentDTO;
import com.cookingchallenges.domain.user.dto.UserDTO;
import lombok.Value;

//@Value
public record CommentDTO(Long id, String text, UserDTO userDTO, ContentDTO contentDTO) {
}
