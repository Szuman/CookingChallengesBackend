package com.cookingchallenges.api;

import com.cookingchallenges.domain.content.ContentFacade;
import com.cookingchallenges.domain.content.dto.ContentDTO;
import com.cookingchallenges.domain.content.dto.EditContent;
import com.cookingchallenges.domain.content.dto.PostContent;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/{id}")
    ContentDTO getContentById(@PathVariable Long id) {
        return contentFacade.getContentById(id);
    }

    @GetMapping
    List<ContentDTO> getContentByName(@RequestParam String title) {
        return contentFacade.getContentByName(title);
    }

    @PostMapping
    ResponseEntity<Void> postContent(@Valid @RequestBody PostContent postContent) {
        Long ContentId = contentFacade.postContent(postContent);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(ContentId).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    void putContent(@Valid @RequestBody EditContent editContent, @PathVariable Long id) {
        contentFacade.editContent(editContent);
        //        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest().path("/{id}")
//                .buildAndExpand(ContentId).toUri();
    }

    @DeleteMapping("/{id}")
    void deleteContent(@PathVariable Long id) {
        contentFacade.deleteContent(id);
    }

//-------------------------------------------------------------------------------------------

//    @GetMapping
//    List<ScreeningBasicInfo> getScreeningsByDate(@RequestParam(value = "from", required = false)
//                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//                                                 LocalDateTime from,
//                                                 @RequestParam(value = "to", required = false)
//                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//                                                 LocalDateTime to,
//                                                 @PageableDefault(sort = {"movie.title", "startTime"})
//                                                 Pageable pageable) {
//        return contentFacade.findScreeningsByDateTimeInterval(from, to, pageable);
//    }

}
