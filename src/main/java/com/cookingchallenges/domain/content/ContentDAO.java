package com.cookingchallenges.domain.content;

import com.cookingchallenges.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface ContentDAO {
    Optional<Content> findById(Long id);
    List<Content> findByTitle(String title);
    Long save(Content content);
    void deleteById(Long id);
    List<Content> findByCreator(User creator);
    void deleteAllByUser(User creator);
}
