package com.cookingchallenges.adapters.content;

import com.cookingchallenges.domain.content.Content;
import com.cookingchallenges.domain.content.ContentDAO;
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
}
