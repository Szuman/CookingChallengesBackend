package com.cookingchallenges.domain.comment;

import com.cookingchallenges.domain.comment.dto.CommentDTO;
import com.cookingchallenges.domain.comment.dto.EditComment;
import com.cookingchallenges.domain.comment.dto.PostComment;
import com.cookingchallenges.domain.comment.exception.CommentNotFoundException;
import com.cookingchallenges.domain.content.Content;
import com.cookingchallenges.domain.content.ContentFacade;
import com.cookingchallenges.domain.user.User;
import com.cookingchallenges.domain.user.UserFacade;
import com.cookingchallenges.mappers.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentApiFacade {
    private final CommentDAO commentDao;
    private final ContentFacade contentFacade;
    private final UserFacade userFacade;

    public List<CommentDTO> findByContentId(Long id) {
        Content content = contentFacade.getContentById(id);
        return CommentMapper.map(commentDao.findByContent(content));
    }

    public List<CommentDTO> findByUserId(Long id) {
        User user = userFacade.getUserById(id);
        return CommentMapper.map(commentDao.findByUser(user));
    }
    @Transactional
    public Long postComment(PostComment postComment) {
        Content content = contentFacade.getContentById(postComment.contentId());
        User user = userFacade.getUserById(postComment.userId());
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
