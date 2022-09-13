package com.cookingchallenges.mappers;

import com.cookingchallenges.domain.content.Content;
import com.cookingchallenges.domain.content.dto.ContentDTO;
import com.cookingchallenges.domain.user.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ContentMapper {
    private ContentMapper() {
    }

    public static ContentDTO map(Content content) {
        UserDTO creator = UserMapper.map(content.getCreator());
        return new ContentDTO(
                content.getId(),
                content.getTitle(),
                content.getType().toString(),
                creator,
                content.getDescription(),
                content.getProducts()
        );
    }

    public static List<ContentDTO> map(List<Content> contents) {
        return contents
                .stream()
                .map(ContentMapper::map)
                .collect(Collectors.toList());
    }

}
