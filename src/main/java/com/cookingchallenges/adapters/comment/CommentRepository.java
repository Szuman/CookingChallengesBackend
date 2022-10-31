package com.cookingchallenges.adapters.comment;

import com.cookingchallenges.domain.comment.Comment;
import com.cookingchallenges.domain.content.Content;
import com.cookingchallenges.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findAll();
    List<Comment> findCommentByCreator(User creator);
    List<Comment> findCommentsByContent(Content content);
    void deleteAllByCreator(User creator);
    void deleteAllByContent(Content content);
}