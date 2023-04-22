package com.cookingchallenges.domain.content.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public record PostContent(@Size(min = 3, max = 48, message = "Title must have between 3 and 48 characters") String title, String type,
                          @NotEmpty(message = "No user chosen!") Long userId, List<String> products,
                          @Size(max = 255, message = "Description must have not more than 255 characters") String description) {
}
