package com.cookingchallenges.domain.comment;

import com.cookingchallenges.domain.comment.dto.CommentDTO;
import com.cookingchallenges.domain.comment.dto.EditComment;
import com.cookingchallenges.domain.comment.dto.PostComment;
import com.cookingchallenges.domain.comment.exception.CommentNotFoundException;
import com.cookingchallenges.domain.content.Content;
import com.cookingchallenges.domain.content.ContentDAO;
import com.cookingchallenges.domain.content.exception.ContentNotFoundException;
import com.cookingchallenges.domain.user.User;
import com.cookingchallenges.domain.user.UserDAO;
import com.cookingchallenges.domain.user.exception.UserNotFoundException;
import com.cookingchallenges.mappers.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentFacade {
    private final CommentDAO commentDao;
    private final ContentDAO contentDAO;
    private final UserDAO userDAO;

    public List<CommentDTO> findByContentId(Long id) {
        Content content = contentDAO.findById(id).orElseThrow(() ->
                new ContentNotFoundException("Content (id=" + id + ") does not exist"));
        return CommentMapper.map(commentDao.findByContent(content));
    }

    public List<CommentDTO> findByUserId(Long id) {
        User user = userDAO.findById(id).orElseThrow(() ->
                new UserNotFoundException("User (id=" + id +") does not exist"));
        return CommentMapper.map(commentDao.findByUser(user));
    }
    @Transactional
    public Long postComment(PostComment postComment) {
        Content content = contentDAO.findById(postComment.contentId()).orElseThrow(() ->
                new ContentNotFoundException("Content (id=" + postComment.contentId() + ") does not exist"));
        User user = userDAO.findById(postComment.userId()).orElseThrow(() ->
                new UserNotFoundException("User (id=" + postComment.userId() +") does not exist"));
        return commentDao.save(new Comment(postComment.text(), user, content));
    }

    @Transactional
    public CommentDTO editComment(EditComment editComment, Long id) {
        Comment comment = commentDao.findById(id).orElseThrow(() ->
                new CommentNotFoundException("Comment (id=" + id + ") does not exist"));
        comment.setText(editComment.text());
        commentDao.save(comment);
        return findById(id);
    }

    @Transactional
    public void deleteComment(Long id) {
        commentDao.deleteById(id);
    }

    public CommentDTO findById(Long id) {
        Comment comment = commentDao.findById(id).orElseThrow(() ->
                new CommentNotFoundException("Comment (id=" + id + ") does not exist"));
        return CommentMapper.map(comment);
    }
}
