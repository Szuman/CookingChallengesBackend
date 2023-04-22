package com.cookingchallenges.api;

import com.cookingchallenges.domain.comment.CommentApiFacade;
import com.cookingchallenges.domain.comment.CommentFixtures;
import com.cookingchallenges.domain.comment.dto.CommentDTO;
import com.cookingchallenges.domain.comment.dto.PostComment;
import com.cookingchallenges.security.WebSecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CommentApiFacade commentApiFacade;

    @Test
    void postComment() throws Exception {
        //given
        Long id = 1L;
        PostComment postComment = CommentFixtures.postComment();
        Mockito.when(commentApiFacade.postComment(postComment)).thenReturn(id);

        //expect
        this.mockMvc.perform(post("/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postComment)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("http://*/comments/" + id));
        Mockito.verify(commentApiFacade).postComment(postComment);
    }

    @Test
    void getById() throws Exception {
        //given
        Long id = 1L;
        CommentDTO commentDTO = CommentFixtures.commentDTOWithId(id);
        Mockito.when(commentApiFacade.findById(id)).thenReturn(commentDTO);

        //expect
        this.mockMvc.perform(get("/bookings/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(commentDTO)));
        Mockito.verify(commentApiFacade).findById(id);
    }

    @Test
    void getByContentId() throws Exception {
        //given
        Long contentId = 1L;
        CommentDTO commentDTO = CommentFixtures.commentDTOWithContentId(contentId);
        Mockito.when(commentApiFacade.findByContentId(contentId)).thenReturn(List.of(commentDTO));

        //expect
        this.mockMvc.perform(get("/comments/content/" + contentId))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(commentDTO)));
        Mockito.verify(commentApiFacade).findByContentId(contentId);
    }

    @Test
    void getByUserId() throws Exception {
        //given
        Long userId = 1L;
        CommentDTO commentDTO = CommentFixtures.commentDTOWithUserId(userId);
        Mockito.when(commentApiFacade.findByUserId(userId)).thenReturn(List.of(commentDTO));

        //expect
        this.mockMvc.perform(get("/comments/user/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(commentDTO)));
        Mockito.verify(commentApiFacade).findByContentId(userId);
    }
}
