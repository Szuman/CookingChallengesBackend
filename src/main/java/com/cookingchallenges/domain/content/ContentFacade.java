package com.cookingchallenges.domain.content;

import com.cookingchallenges.domain.content.dto.ContentDTO;
import com.cookingchallenges.domain.content.exception.ContentNotFoundException;
import com.cookingchallenges.mappers.ContentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentFacade {

    private final ContentDAO contentDao;

    public ContentDTO getContentById(Long id) {
        Content content = contentDao.findById(id).orElseThrow(() ->
                new ContentNotFoundException("Content (id=" + id + ") does not exist"));
        return ContentMapper.map(content);
    }

    public List<ContentDTO> getContentByName(String name) {
        List<Content> contents = contentDao.findByTitle(name);
        return ContentMapper.map(contents);
    }
}
