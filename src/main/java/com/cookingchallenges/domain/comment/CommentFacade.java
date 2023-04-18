package com.cookingchallenges.domain.comment;

import com.cookingchallenges.domain.content.Content;
import com.cookingchallenges.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentFacade {

    private final CommentDAO commentDAO;

    public void deleteAllByCreator(User creator) {
        commentDAO.deleteAllByCreator(creator);
    }

    public void deleteAllByContent(Content content) {
        commentDAO.deleteAllByContent(content);
    }
}
