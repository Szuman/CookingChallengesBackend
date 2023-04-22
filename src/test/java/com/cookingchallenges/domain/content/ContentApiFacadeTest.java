package com.cookingchallenges.domain.content;

import com.cookingchallenges.domain.comment.CommentFacade;
import com.cookingchallenges.domain.content.dto.ContentDTO;
import com.cookingchallenges.domain.content.dto.EditContent;
import com.cookingchallenges.domain.content.dto.PostContent;
import com.cookingchallenges.domain.user.User;
import com.cookingchallenges.domain.user.UserFacade;
import com.cookingchallenges.domain.user.UserFixtures;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.cookingchallenges.domain.content.ContentFixtures.DUMMY_CONTENT_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ContentApiFacadeTest {

    @Mock
    ContentDAO contentDao;

    @Mock
    UserFacade userFacade;

    @Mock
    CommentFacade commentFacade;

    @InjectMocks
    ContentApiFacade contentApiFacade;

    @Test
    void getContentById() {
        //given
        Long id = 1L;
        ContentDTO expected = ContentFixtures.contentDTOWithId(id);
        Content content = ContentFixtures.contentWithId(id);
        when(contentDao.findById(id)).thenReturn(Optional.of(content));

        //when
        ContentDTO actual = contentApiFacade.getContentById(id);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getContentByTitle() {
        //given
        String title = "Hotcakes";
        ContentDTO contentDTO = ContentFixtures.contentDTOWithTitle(title);
        List<ContentDTO> expected = List.of(contentDTO);

        Content content = ContentFixtures.contentWithTitle(title);
        when(contentDao.findByTitle(title)).thenReturn(List.of(content));

        //when
        List<ContentDTO> actual = contentApiFacade.getContentByTitle(title);

        //then
        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    void getContentByUserId() {
        //given
        Long userId = 1L;
        ContentDTO contentDTO = ContentFixtures.contentDTOWithUserId(userId);
        List<ContentDTO> expected = List.of(contentDTO);

        User user = UserFixtures.user(userId);
        when(userFacade.getUserById(userId)).thenReturn(user);
        Content content = ContentFixtures.contentWithUserId(userId);
        when(contentDao.findByCreator(user)).thenReturn(List.of(content));

        //when
        List<ContentDTO> actual = contentApiFacade.getContentByUserId(userId);

        //then
        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    void postContent() {
        //given
        Long expected = 1L;
        Long userId = 2L;
        PostContent postContent = ContentFixtures.postContentWithUserId(userId);

        User user = UserFixtures.user(userId);
        when(userFacade.getUserById(userId)).thenReturn(user);
        Content content = ContentFixtures.noIdContentWithUserId(userId);
        when(contentDao.save(content)).thenReturn(expected);

        //when
        Long actual = contentApiFacade.postContent(postContent);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void editContent() {
        //given
        Long id = DUMMY_CONTENT_ID;
        String title = "Hotcakes";
        EditContent editContent = ContentFixtures.editContentWithTitle(title);
        Content editedContent = ContentFixtures.contentWithTitle(title);
        ContentDTO expected = ContentFixtures.contentDTOWithTitle(title);

        Content content = ContentFixtures.contentWithId(id);
        when(contentDao.findById(id)).thenReturn(Optional.of(content));

        //when
        ContentDTO actual = contentApiFacade.editContent(editContent, id);

        //then
        assertThat(actual).isEqualTo(expected);
        verify(contentDao).save(editedContent);
    }

    @Test
    void deleteContent() {
        //given
        Long id = 1L;
        Content content = ContentFixtures.contentWithId(id);
        when(contentDao.findById(id)).thenReturn(Optional.of(content));

        //when
        contentApiFacade.deleteContent(id);

        //then
        verify(commentFacade).deleteAllByContent(content);
        verify(contentDao).deleteById(id);
    }
}