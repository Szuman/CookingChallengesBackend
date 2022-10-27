package com.cookingchallenges.domain.content;

import com.cookingchallenges.domain.content.dto.ContentDTO;
import com.cookingchallenges.domain.content.dto.EditContent;
import com.cookingchallenges.domain.content.dto.PostContent;
import com.cookingchallenges.domain.content.exception.ContentNotFoundException;
import com.cookingchallenges.domain.user.User;
import com.cookingchallenges.domain.user.UserDAO;
import com.cookingchallenges.mappers.ContentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentFacade {

    private final ContentDAO contentDao;
    private final UserDAO userDAO;

    public ContentDTO getContentById(Long id) {
        Content content = contentDao.findById(id).orElseThrow(() ->
                new ContentNotFoundException("Content (id=" + id + ") does not exist"));
        return ContentMapper.map(content);
    }

    public List<ContentDTO> getContentByName(String name) {
        List<Content> contents = contentDao.findByTitle(name);
        return ContentMapper.map(contents);
    }

    public Long postContent(PostContent postContent) {
        User user = userDAO.findById(postContent.userId()).orElseThrow(() ->
                new ContentNotFoundException("User (id=" + postContent.userId() + ") does not exist"));
        Type type = Type.valueOf(postContent.type());
        return contentDao.save(new Content(postContent.title(), type, user, postContent.products(), postContent.description()));
    }

    public void editContent(EditContent editContent) {
    }

    public void deleteContent(Long id) {
    }
}
