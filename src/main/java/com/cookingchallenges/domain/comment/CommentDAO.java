package com.cookingchallenges.domain.comment;

import com.cookingchallenges.domain.content.Content;
import com.cookingchallenges.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface CommentDAO {
    Optional<Comment> findById(Long id);
    List<Comment> findByContent(Content content);
    List<Comment> findByUser(User user);
    Long save(Comment comment);
    void delete(Comment comment);
    void deleteAllByCreator(User creator);
    void deleteAllByContent(Content content);
}
