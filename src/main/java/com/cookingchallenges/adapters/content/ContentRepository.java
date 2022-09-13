package com.cookingchallenges.adapters.content;

import com.cookingchallenges.domain.content.Content;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface ContentRepository extends PagingAndSortingRepository<Content, Long> {
    Optional<Content> findById(Long id);
    List<Content> findContentByTitle(String title);
}
