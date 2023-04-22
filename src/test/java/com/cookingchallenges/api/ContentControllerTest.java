package com.cookingchallenges.api;

import com.cookingchallenges.domain.content.ContentApiFacade;
import com.cookingchallenges.domain.content.ContentFixtures;
import com.cookingchallenges.domain.content.dto.ContentDTO;
import com.cookingchallenges.domain.content.dto.PostContent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
@WebMvcTest(value = ContentController.class)//, excludeAutoConfiguration = WebSecurityConfig.class)
class ContentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ContentApiFacade contentApiFacade;

    @Test
    void postContent() throws Exception {
        //given
        Long id = 1L;
        PostContent postContent = ContentFixtures.postContent();
        Mockito.when(contentApiFacade.postContent(postContent)).thenReturn(id);

        //expect
        this.mockMvc.perform(post("/contents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postContent)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("http://*/contents/" + id));
        Mockito.verify(contentApiFacade).postContent(postContent);
    }

    @Test
    void getById() throws Exception {
        //given
        Long id = 1L;
        ContentDTO contentDTO = ContentFixtures.contentDTOWithId(id);
        Mockito.when(contentApiFacade.getContentById(id)).thenReturn(contentDTO);

        //expect
        this.mockMvc.perform(get("/contents/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(contentDTO)));
        Mockito.verify(contentApiFacade).getContentById(id);
    }

    @Test
    void getByContentId() throws Exception {
        //given
        String name = "Hotcakes";
        ContentDTO contentDTO = ContentFixtures.contentDTOWithTitle(name);
        Mockito.when(contentApiFacade.getContentByTitle(name)).thenReturn(List.of(contentDTO));

        //expect
        this.mockMvc.perform(get("/contents?title=" + name))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(contentDTO)));
        Mockito.verify(contentApiFacade).getContentByTitle(name);
    }

    @Test
    void getByUserId() throws Exception {
        //given
        Long id = 1L;
        ContentDTO contentDTO = ContentFixtures.contentDTOWithId(id);
        Mockito.when(contentApiFacade.getContentByUserId(id)).thenReturn(List.of(contentDTO));

        //expect
        this.mockMvc.perform(get("/comments/user/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(contentDTO)));
        Mockito.verify(contentApiFacade).getContentByUserId(id);
    }
}
