package com.cookingchallenges.domain.comment.dto;

import javax.validation.constraints.NotEmpty;

public record PostComment(String text, @NotEmpty(message = "No user chosen!") Long userId,
                          @NotEmpty(message = "No challenge/recipe chosen!") Long contentId) {
}
