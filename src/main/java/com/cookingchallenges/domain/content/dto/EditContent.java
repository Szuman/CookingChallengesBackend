package com.cookingchallenges.domain.content.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public record EditContent(
        @Size(min = 3, max = 48, message = "Title must have between 3 and 48 characters") String title,
        @NotEmpty(message = "No user chosen!") Long userId, String products, String description) {
}
