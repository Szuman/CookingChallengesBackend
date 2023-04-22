package com.cookingchallenges.domain.comment.dto;

import javax.validation.constraints.Size;

public record EditComment(@Size(max = 255, message = "Comment must have not more than 255 characters") String text) {
}
