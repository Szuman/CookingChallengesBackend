package com.cookingchallenges.domain.comment.dto;

import javax.validation.constraints.Size;


public record PostComment(@Size(max = 255, message = "Comment must have not more than 255 characters") String text,
                          Long userId, Long contentId) {
}
