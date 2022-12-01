package com.cookingchallenges.domain.content.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public record EditContent(
        @Size(min = 3, max = 48, message = "Title must have between 3 and 48 characters") String title, List<String> products, String description) {
}
