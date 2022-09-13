package com.cookingchallenges.domain.comment;

import com.cookingchallenges.domain.content.Content;
import com.cookingchallenges.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface CommentDAO {
    List<Comment> findAll();
    List<Comment> findByContent(Content content);
    List<Comment> findByUser(User user);
}
