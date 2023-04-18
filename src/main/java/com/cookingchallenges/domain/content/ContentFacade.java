package com.cookingchallenges.domain.content;

import com.cookingchallenges.domain.content.exception.ContentNotFoundException;
import com.cookingchallenges.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContentFacade {
    
    private final ContentDAO contentDAO;

    public Content getContentById(Long id) {
        return contentDAO.findById(id).orElseThrow(() ->
                new ContentNotFoundException("Content (id=" + id + ") does not exist"));
    }

    public void deleteAllByUser(User creator) {
        contentDAO.deleteAllByUser(creator);
    }
}
