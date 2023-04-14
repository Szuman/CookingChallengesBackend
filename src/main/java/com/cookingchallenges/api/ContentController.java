package com.cookingchallenges.api;

import com.cookingchallenges.domain.content.ContentFacade;
import com.cookingchallenges.domain.content.dto.ContentDTO;
import com.cookingchallenges.domain.content.dto.EditContent;
import com.cookingchallenges.domain.content.dto.PostContent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("content")
class ContentController {

    private final ContentFacade contentFacade;

    @Operation(summary = "Get content by id", description = "Get content by id")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{id}")
    ContentDTO getContentById(@PathVariable Long id) {
        return contentFacade.getContentById(id);
    }

    @Operation(summary = "Get content by name", description = "Get content by its title")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    List<ContentDTO> getContentByName(@RequestParam String title) {
        return contentFacade.getContentByName(title);
    }

    @Operation(summary = "Get content by user", description = "Get content by users id")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/user/{id}")
    List<ContentDTO> getContentByUserId(@PathVariable Long id) {
        return contentFacade.getContentByUserId(id);
    }

    @Operation(summary = "Post content", description = "Post new content")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    ResponseEntity<Void> postContent(@Valid @RequestBody PostContent postContent) {
        Long contentId = contentFacade.postContent(postContent);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(contentId).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Edit content", description = "Edit content")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}")
    ResponseEntity<ContentDTO> putContent(@Valid @RequestBody EditContent editContent, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(contentFacade.editContent(editContent, id));
    }

    @Operation(summary = "Delete content", description = "Delete content")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/{id}")
    void deleteContent(@PathVariable Long id) {
        contentFacade.deleteContent(id);
    }


}
