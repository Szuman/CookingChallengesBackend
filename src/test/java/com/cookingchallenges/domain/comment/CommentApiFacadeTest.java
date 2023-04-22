package com.cookingchallenges.domain.comment;

import com.cookingchallenges.domain.comment.dto.CommentDTO;
import com.cookingchallenges.domain.comment.dto.EditComment;
import com.cookingchallenges.domain.comment.dto.PostComment;
import com.cookingchallenges.domain.content.Content;
import com.cookingchallenges.domain.content.ContentFacade;
import com.cookingchallenges.domain.content.ContentFixtures;
import com.cookingchallenges.domain.user.User;
import com.cookingchallenges.domain.user.UserFacade;
import com.cookingchallenges.domain.user.UserFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentApiFacadeTest {

    @Mock
    CommentDAO commentDao;

    @Mock
    ContentFacade contentFacade;

    @Mock
    UserFacade userFacade;

    CommentApiFacade commentApiFacade;

    @BeforeEach
    void init() {
        commentApiFacade = new CommentApiFacade(commentDao, contentFacade, userFacade);
    }

    @Test
    void findByContentId() {
        //given
        Long id = 1L;
        List<CommentDTO> expected = List.of(CommentFixtures.commentDTOWithId(1L));
        Content content = ContentFixtures.contentWithId(id);
        Comment comment = CommentFixtures.comment(id);
        when(contentFacade.getContentById(id)).thenReturn(content);
        when(commentDao.findById(id)).thenReturn(Optional.of(comment));

        //when
        List<CommentDTO> actual = commentApiFacade.findByContentId(id);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findByUserId() {
        //given
        Long userId = 1L;
        List<CommentDTO> expected = List.of(CommentFixtures.commentDTOWithId(1L));
        when(userFacade.getUserById(userId)).thenReturn(UserFixtures.user(userId));

        //when
        List<CommentDTO> actual = commentApiFacade.findByUserId(userId);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void postComment() {
        //given
        Long expected = 1L;
        Long contentId = 1L;
        Long userId = 1L;

        PostComment postComment = CommentFixtures.postCommentWithContentId(contentId);
        Content content = ContentFixtures.contentWithId(contentId);
        User user = UserFixtures.user(userId);

        when(contentFacade.getContentById(contentId)).thenReturn(content);
        when(userFacade.getUserById(userId)).thenReturn(user);

        //when
        Long actual = commentApiFacade.postComment(postComment);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void editComment() {
        //given
        Long id = 1L;
        String text = "updated comment";
        EditComment editComment = CommentFixtures.editComment(text);
        CommentDTO expected = CommentFixtures.commentDTOWithUserId(id);
        Comment comment = CommentFixtures.comment(id);
        when(commentDao.findById(id)).thenReturn(Optional.of(comment));

        //when
        CommentDTO actual = commentApiFacade.editComment(editComment, id);

        //then
        assertThat(actual).isEqualTo(expected);
        verify(commentDao).save(comment);
    }

//    @Test
//    void deleteComment() {
//        //given
//        Long id = 1L;
//
//        //when
//        commentApiFacade.deleteComment(id);
//
//        //then
//        Mockito.verify(commentDao).delete(id);
//    }

    @Test
    void findById() {
        //given
        Long id = 1L;
        CommentDTO expected = CommentFixtures.commentDTOWithId(id);
        Comment comment = CommentFixtures.comment(id);
        when(commentDao.findById(id)).thenReturn(Optional.of(comment));

        //when
        CommentDTO actual = commentApiFacade.findById(id);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}