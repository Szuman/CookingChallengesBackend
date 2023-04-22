package com.cookingchallenges.adapters.comment;

import com.cookingchallenges.domain.comment.Comment;
import com.cookingchallenges.domain.comment.CommentDAO;
import com.cookingchallenges.domain.content.Content;
import com.cookingchallenges.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class CommentDAOAdapter implements CommentDAO {
    private final CommentRepository commentRepository;

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findByContent(Content content) {
        return commentRepository.findCommentsByContent(content);
    }

    @Override
    public List<Comment> findByUser(User user) {
        return commentRepository.findCommentByCreator(user);
    }

    @Override
    public Long save(Comment comment) {
        return commentRepository.save(comment).getId();
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public void deleteAllByCreator(User creator) {
        commentRepository.deleteAllByCreator(creator);
    }

    @Override
    public void deleteAllByContent(Content content) {
        commentRepository.deleteAllByContent(content);
    }

}
