package com.cookingchallenges.mappers;

import com.cookingchallenges.domain.comment.Comment;
import com.cookingchallenges.domain.comment.dto.CommentDTO;
import com.cookingchallenges.domain.content.dto.ContentDTO;
import com.cookingchallenges.domain.user.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {
    private CommentMapper() {
    }

    public static CommentDTO map(Comment comment) {
        UserDTO creator = UserMapper.map(comment.getCreator());
        ContentDTO content = ContentMapper.map(comment.getContent());
        return new CommentDTO(
                comment.getId(),
                comment.getText(),
                creator,
                content
        );
    }

    public static List<CommentDTO> map(List<Comment> comments) {
        return comments
                .stream()
                .map(CommentMapper::map)
                .collect(Collectors.toList());
    }
}
