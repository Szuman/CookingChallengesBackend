package com.cookingchallenges.domain.content;

import com.cookingchallenges.domain.comment.CommentFacade;
import com.cookingchallenges.domain.content.dto.ContentDTO;
import com.cookingchallenges.domain.content.dto.EditContent;
import com.cookingchallenges.domain.content.dto.PostContent;
import com.cookingchallenges.domain.content.exception.ContentNotFoundException;
import com.cookingchallenges.domain.user.User;
import com.cookingchallenges.domain.user.UserFacade;
import com.cookingchallenges.mappers.ContentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentApiFacade {

    private final ContentDAO contentDao;
    private final UserFacade userFacade;
    private final CommentFacade commentFacade;

    public ContentDTO getContentById(Long id) {
        Content content = contentDao.findById(id).orElseThrow(() ->
                new ContentNotFoundException("Content (id=" + id + ") does not exist"));
        return ContentMapper.map(content);
    }

    public List<ContentDTO> getContentByTitle(String title) {
        List<Content> contents = contentDao.findByTitle(title);
        return ContentMapper.map(contents);
    }

    @Transactional
    public Long postContent(PostContent postContent) {
        User user = userFacade.getUserById(postContent.userId());
        Type type = Type.valueOf(postContent.type());
        return contentDao.save(new Content(postContent.title(), type, user, postContent.products(), postContent.description()));
    }

    @Transactional
    public ContentDTO editContent(EditContent editContent, Long id) {
        Content content = contentDao.findById(id).orElseThrow(() ->
                new ContentNotFoundException("Content (id=" + id + ") does not exist"));
        content.setTitle(editContent.title());
        content.setDescription(editContent.description());
        content.setProducts(editContent.products());
        contentDao.save(content);
        return getContentById(id);
    }

    @Transactional
    public void deleteContent(Long id) {
        Content content = contentDao.findById(id).orElseThrow(() ->
                new ContentNotFoundException("Content (id=" + id + ") does not exist"));
        commentFacade.deleteAllByContent(content);
        contentDao.deleteById(id);
    }

    public List<ContentDTO> getContentByUserId(Long id) {
        User user = userFacade.getUserById(id);
        return ContentMapper.map(contentDao.findByCreator(user));
    }
}
