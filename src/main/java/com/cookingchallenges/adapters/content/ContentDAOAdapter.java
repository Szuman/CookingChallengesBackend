package com.cookingchallenges.adapters.content;

import com.cookingchallenges.domain.content.Content;
import com.cookingchallenges.domain.content.ContentDAO;
import com.cookingchallenges.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class ContentDAOAdapter implements ContentDAO {
    private final ContentRepository contentRepository;
    @Override
    public Optional<Content> findById(Long id) {
        return contentRepository.findById(id);
    }

    @Override
    public List<Content> findByTitle(String title) {
        return contentRepository.findContentByTitle(title);
    }

    @Override
    public Long save(Content content) {
        return contentRepository.save(content).getId();
    }

    @Override
    public void deleteById(Long id) {
        contentRepository.deleteById(id);
    }

    @Override
    public List<Content> findByCreator(User creator) {
        return contentRepository.findByCreator(creator);
    }

    @Override
    public void deleteAllByUser(User creator) {
        contentRepository.deleteAllByCreator(creator);
    }

}
